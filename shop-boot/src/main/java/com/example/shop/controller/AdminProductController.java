package com.example.shop.controller;

import com.example.shop.entity.Product;
import com.example.shop.service.ProductService;
import com.example.shop.util.FileUploadUtil;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 后台商品管理控制器
 * 处理后台商品相关的请求
 *
 * @author example
 * @version 1.0.0
 */
@Slf4j
@Controller
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;
    private final FileUploadUtil fileUploadUtil;

    /**
     * 检查管理员是否登录
     */
    private boolean checkAdminLogin(HttpSession session) {
        return session.getAttribute("adminUsername") != null;
    }

    /**
     * 后台商品列表(分页)
     */
    @GetMapping("/list")
    public String list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            Model model,
            HttpSession session) {
        if (!checkAdminLogin(session)) {
            return "redirect:/admin/login";
        }
        log.debug("查询后台商品列表, pageNum={}, pageSize={}", pageNum, pageSize);
        PageInfo<Product> pageInfo = productService.listByPage(pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "product/list";
    }

    /**
     * 跳转到添加商品页面
     */
    @GetMapping("/add")
    public String toAdd(HttpSession session) {
        if (!checkAdminLogin(session)) {
            return "redirect:/admin/login";
        }
        return "product/add";
    }

    /**
     * 添加商品
     */
    @PostMapping("/save")
    public String add(@ModelAttribute Product product,
                      @RequestParam("file") MultipartFile file,
                      HttpServletRequest request,
                      HttpSession session) {
        if (!checkAdminLogin(session)) {
            return "redirect:/admin/login";
        }
        log.info("添加商品: {}", product.getProductName());

        try {
            // 上传图片
            if (!file.isEmpty()) {
                String fileName = fileUploadUtil.upload(file, request);
                product.setPicture(fileName);
                log.debug("商品图片上传成功: {}", fileName);
            }

            productService.addProduct(product);
            log.info("商品添加成功: {}", product.getProductName());
        } catch (Exception e) {
            log.error("商品添加失败: {}", e.getMessage(), e);
        }

        return "redirect:/admin/product/list";
    }

    /**
     * 跳转到编辑商品页面
     */
    @GetMapping("/edit")
    public String toEdit(@RequestParam Integer id, Model model, HttpSession session) {
        if (!checkAdminLogin(session)) {
            return "redirect:/admin/login";
        }
        log.debug("跳转到编辑商品页面, productId={}", id);
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product/edit";
    }

    /**
     * 更新商品
     */
    @PostMapping("/update")
    public String update(@ModelAttribute Product product,
                         @RequestParam(value = "file", required = false) MultipartFile file,
                         HttpServletRequest request,
                         HttpSession session) {
        if (!checkAdminLogin(session)) {
            return "redirect:/admin/login";
        }
        log.info("更新商品, productId={}", product.getProductId());

        try {
            // 上传新图片
            if (file != null && !file.isEmpty()) {
                String fileName = fileUploadUtil.upload(file, request);
                product.setPicture(fileName);
                log.debug("商品图片更新成功: {}", fileName);
            }

            productService.updateProduct(product);
            log.info("商品更新成功, productId={}", product.getProductId());
        } catch (Exception e) {
            log.error("商品更新失败: {}", e.getMessage(), e);
        }

        return "redirect:/admin/product/list";
    }

    /**
     * 删除商品
     */
    @GetMapping("/delete")
    public String delete(@RequestParam Integer id, HttpSession session) {
        if (!checkAdminLogin(session)) {
            return "redirect:/admin/login";
        }
        log.info("删除商品, productId={}", id);
        productService.deleteProduct(id);
        return "redirect:/admin/product/list";
    }
}
