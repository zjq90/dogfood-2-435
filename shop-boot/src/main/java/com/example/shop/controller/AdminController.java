package com.example.shop.controller;

import com.example.shop.entity.Admin;
import com.example.shop.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 管理员控制器
 * 处理管理员相关的请求
 *
 * @author example
 * @version 1.0.0
 */
@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    /**
     * 后台管理首页
     */
    @GetMapping({"", "/", "/index"})
    public String index(HttpSession session) {
        if (session.getAttribute("adminUsername") == null) {
            session.setAttribute("errorMsg", "请先登录");
            return "redirect:/admin/login";
        }
        return "index";
    }

    /**
     * 跳转到登录页面
     */
    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }

    /**
     * 管理员登录
     */
    @PostMapping("/login")
    public String login(@ModelAttribute Admin admin, HttpSession session) {
        try {
            Admin existAdmin = adminService.login(admin.getUsername(), admin.getPassword());
            session.setAttribute("adminUsername", existAdmin.getUsername());
            session.setAttribute("adminId", existAdmin.getAdminId());
            session.removeAttribute("errorMsg");
            log.info("管理员[{}]登录成功", existAdmin.getUsername());
            return "redirect:/admin/";
        } catch (Exception e) {
            log.warn("管理员登录失败: {}", e.getMessage());
            session.setAttribute("errorMsg", "用户名或密码错误");
            return "redirect:/admin/login";
        }
    }

    /**
     * 管理员登出
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        String username = (String) session.getAttribute("adminUsername");
        if (username != null) {
            log.info("管理员[{}]登出", username);
        }
        session.removeAttribute("adminUsername");
        session.removeAttribute("adminId");
        return "redirect:/admin/login";
    }
}
