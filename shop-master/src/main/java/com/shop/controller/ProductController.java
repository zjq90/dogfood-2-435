package com.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.entity.Product;
import com.shop.service.ProductService;
import com.shop.util.IPTimeStamp;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 商品控制器
 * 处理商品管理相关请求
 * 
 * @author shop
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Value("${file.upload.path}")
    private String uploadPath;

    @Autowired
    private ProductService productService;

    /**
     * 商品列表（后台）
     */
    @GetMapping("/list")
    public String listProduct(Model model) {
        logger.debug("查询商品列表");
        List<Product> list = productService.listProduct();
        model.addAttribute("list", list);
        return "product/list";
    }

    /**
     * 跳转到添加商品页面
     */
    @GetMapping("/add")
    public String toAddProduct() {
        return "product/add";
    }

    /**
     * 添加商品
     */
    @PostMapping("/add")
    public String addProduct(Product product, @RequestParam("file") MultipartFile file) throws IOException {
        logger.info("添加商品: {}", product.getPname());

        if (!file.isEmpty()) {
            File pathFile = new File(uploadPath);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }

            IPTimeStamp ip = new IPTimeStamp();
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
            String newFileName = ip.getTimeStamp() + "." + ext;

            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(uploadPath, newFileName));
            product.setPic(newFileName);
            logger.debug("上传图片成功: {}", newFileName);
        }

        productService.addProduct(product);
        logger.info("商品添加成功: {}", product.getPname());
        return "redirect:list";
    }

    /**
     * 删除商品
     */
    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("pid") Integer pid) {
        logger.info("删除商品: {}", pid);
        productService.deleteProduct(pid);
        return "redirect:list";
    }

    /**
     * 跳转到编辑商品页面
     */
    @GetMapping("/edit")
    public String toEdit(@RequestParam("pid") Integer pid, Model model) {
        Product product = productService.findById(pid);
        model.addAttribute("product", product);
        return "product/edit";
    }

    /**
     * 编辑商品
     */
    @PostMapping("/edit")
    public String edit(Product product, @RequestParam("file") MultipartFile file) throws IOException {
        logger.info("编辑商品: {}", product.getPid());

        Product oldProduct = productService.findById(product.getPid());

        if (!file.isEmpty() && file.getOriginalFilename() != null && !file.getOriginalFilename().equals("")) {
            File pathFile = new File(uploadPath);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }

            File oldFile = new File(uploadPath + "/" + oldProduct.getPic());
            if (oldFile.exists()) {
                oldFile.delete();
            }

            IPTimeStamp ip = new IPTimeStamp();
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
            String newFileName = ip.getTimeStamp() + "." + ext;

            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(uploadPath, newFileName));
            product.setPic(newFileName);
            logger.debug("更新图片成功: {}", newFileName);
        } else {
            product.setPic(oldProduct.getPic());
        }

        productService.updateProduct(product);
        logger.info("商品更新成功: {}", product.getPid());
        return "redirect:list";
    }

    /**
     * 根据名称查询商品
     */
    @PostMapping("/find")
    public String findByPname(@RequestParam("pname") String pname, Model model) {
        logger.debug("根据名称查询商品: {}", pname);
        List<Product> list = productService.findByName(pname);
        model.addAttribute("list", list);
        return "product/list";
    }

    /**
     * 前台商品分页列表
     */
    @GetMapping("/frontlist")
    public String frontList(@RequestParam(value = "pageNum", required = false) Integer pageNum,
                            @RequestParam(value = "pageSize", required = false) Integer pageSize,
                            HttpSession session) {
        logger.debug("前台商品分页查询，页码: {}, 每页数量: {}", pageNum, pageSize);
        PageHelper.startPage(pageNum == null ? 1 : pageNum, pageSize == null ? 5 : pageSize);
        List<Product> list = productService.listProduct();
        PageInfo<Product> pages = new PageInfo<>(list);
        session.setAttribute("pages", pages);
        return "forward:/index.jsp";
    }

    /**
     * 前台商品详情
     */
    @GetMapping("/detail")
    public String detail(@RequestParam("pid") Integer pid, Model model) {
        logger.debug("查询商品详情: {}", pid);
        Product product = productService.findById(pid);
        model.addAttribute("product", product);
        return "forward:/detail.jsp";
    }
}
