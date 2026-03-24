package cn.edu.nuc.shop.controller;

import cn.edu.nuc.shop.entity.Product;
import cn.edu.nuc.shop.service.ProductService;
import cn.edu.nuc.shop.util.IPTimeStamp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 商品控制器
 * 处理商品的增删改查、分页查询等请求
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    /**
     * 后台商品列表
     * @param model 模型对象
     * @return 商品列表页面
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listProduct(Model model) {
        logger.debug("查询商品列表");
        List<Product> list = productService.findAll();
        model.addAttribute("list", list);
        logger.debug("商品数量: {}", list.size());
        return "product/list";
    }

    /**
     * 跳转到添加商品页面
     * @return 添加商品页面
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAddProduct() {
        logger.debug("跳转到添加商品页面");
        return "product/add";
    }

    /**
     * 添加商品（带图片上传）
     * @param product 商品信息
     * @param file 商品图片
     * @param request 请求对象
     * @return 重定向到商品列表
     * @throws IOException IO异常
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addProduct(Product product, @RequestParam("file") MultipartFile file,
                             HttpServletRequest request) throws IOException {

        logger.info("添加商品: {}", product.getPname());

        // 处理文件上传
        if (!file.isEmpty()) {
            String realPath = request.getSession().getServletContext().getRealPath("/upload");
            File pathFile = new File(realPath);

            if (!pathFile.exists()) {
                boolean created = pathFile.mkdirs();
                logger.debug("创建上传目录: {}, 结果: {}", realPath, created);
            }

            logger.debug("文件类型: {}, 文件名称: {}, 文件大小: {}",
                    file.getContentType(), file.getOriginalFilename(), file.getSize());

            IPTimeStamp ipTimeStamp = new IPTimeStamp();
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
            String newFilename = ipTimeStamp.getTimeStamp() + "." + ext;

            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, newFilename));
            product.setPic(newFilename);
            logger.debug("文件上传成功，新文件名: {}", newFilename);
        }

        productService.addProduct(product);
        logger.info("商品添加成功: {}", product.getPname());

        return "redirect:list";
    }

    /**
     * 删除商品
     * @param product 商品信息（含商品ID）
     * @return 重定向到商品列表
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteProduct(Product product) {
        logger.info("删除商品: {}", product.getPid());
        productService.delete(product.getPid());
        logger.info("商品删除成功: {}", product.getPid());
        return "redirect:list";
    }

    /**
     * 跳转到编辑商品页面
     * @param product 商品信息（含商品ID）
     * @param map 模型对象
     * @return 编辑商品页面
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String toEdit(Product product, ModelMap map) {
        logger.debug("跳转到编辑商品页面，商品ID: {}", product.getPid());
        Product pro = productService.findById(product.getPid());
        map.addAttribute("product", pro);
        return "product/edit";
    }

    /**
     * 编辑商品（带图片上传）
     * @param product 商品信息
     * @param file 商品图片
     * @param request 请求对象
     * @return 重定向到商品列表
     * @throws IOException IO异常
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Product product, @RequestParam("file") MultipartFile file,
                       HttpServletRequest request) throws IOException {

        logger.info("编辑商品: {}", product.getPid());

        Product oldProduct = productService.findById(product.getPid());

        // 如果上传了新图片
        if (file.getOriginalFilename() != null && !file.getOriginalFilename().equals("")) {
            String realPath = request.getSession().getServletContext().getRealPath("/upload");

            // 删除旧图片
            File oldFile = new File(realPath + "/" + oldProduct.getPic());
            if (oldFile.exists()) {
                boolean deleted = oldFile.delete();
                logger.debug("删除旧图片: {}, 结果: {}", oldProduct.getPic(), deleted);
            }

            File pathFile = new File(realPath);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }

            logger.debug("上传新文件: {}", file.getOriginalFilename());

            IPTimeStamp ipTimeStamp = new IPTimeStamp();
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
            String newFilename = ipTimeStamp.getTimeStamp() + "." + ext;

            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, newFilename));
            product.setPic(newFilename);
            logger.debug("新图片上传成功: {}", newFilename);

        } else {
            // 没有上传新图片，保留旧图片
            product.setPic(oldProduct.getPic());
        }

        productService.update(product);
        logger.info("商品编辑成功: {}", product.getPid());

        return "redirect:list";
    }

    /**
     * 根据商品名称搜索
     * @param pname 商品名称
     * @param model 模型对象
     * @return 商品列表页面
     */
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public String findByPname(@RequestParam("pname") String pname, Model model) {
        logger.info("搜索商品，名称: {}", pname);
        List<Product> list = productService.findByNameLike(pname);
        model.addAttribute("list", list);
        logger.debug("搜索结果数量: {}", list.size());
        return "product/list";
    }

    /**
     * 前台商品分页列表
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param session Session对象
     * @return 首页
     */
    @RequestMapping(value = "/frontlist", method = RequestMethod.GET)
    public String pageList(Integer pageNum, Integer pageSize, HttpSession session) {
        int actualPageNum = (pageNum == null) ? 1 : pageNum;
        int actualPageSize = (pageSize == null) ? 5 : pageSize;

        logger.debug("前台商品分页查询，页码: {}, 每页数量: {}", actualPageNum, actualPageSize);

        PageHelper.startPage(actualPageNum, actualPageSize);
        List<Product> list = productService.findAll();
        PageInfo<Product> pages = new PageInfo<>(list);

        session.setAttribute("pages", pages);
        logger.debug("分页信息: 总页数: {}, 总记录数: {}", pages.getPages(), pages.getTotal());

        return "forward:/index.jsp";
    }

    /**
     * 前台商品详情页
     * @param product 商品信息（含商品ID）
     * @param model 模型对象
     * @return 商品详情页
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Product product, Model model) {
        logger.debug("查询商品详情，商品ID: {}", product.getPid());
        Product pro = productService.findById(product.getPid());
        model.addAttribute("product", pro);
        return "forward:/detail.jsp";
    }
}

