package com.example.shop.controller;

import com.example.shop.entity.User;
import com.example.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 用户控制器
 * 处理用户相关的请求
 *
 * @author example
 * @version 1.0.0
 */
@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 跳转到登录页面
     */
    @GetMapping("/login")
    public String toLogin() {
        return "redirect:/login.jsp";
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpSession session) {
        try {
            User existUser = userService.login(user.getUsername(), user.getPassword());
            session.setAttribute("frontUser", existUser.getUsername());
            session.setAttribute("frontUserId", existUser.getUserId());
            session.removeAttribute("errorMsg");
            log.info("用户[{}]登录成功", existUser.getUsername());

            // 检查是否有需要跳转的页面
            String orderPath = (String) session.getAttribute("orderPath");
            if (orderPath != null) {
                session.removeAttribute("orderPath");
                return "redirect:" + orderPath;
            }

            return "redirect:/product/frontlist";
        } catch (Exception e) {
            log.warn("用户登录失败: {}", e.getMessage());
            session.setAttribute("errorMsg", "用户名或密码错误");
            return "forward:/login.jsp";
        }
    }

    /**
     * 用户登出
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        String username = (String) session.getAttribute("frontUser");
        if (username != null) {
            log.info("用户[{}]登出", username);
        }
        session.removeAttribute("frontUser");
        session.removeAttribute("frontUserId");
        return "redirect:/login.jsp";
    }

    /**
     * 检查用户名是否存在
     */
    @GetMapping("/checkUsername")
    @ResponseBody
    public boolean checkUsername(@RequestParam String username) {
        return userService.checkUsernameExists(username);
    }
}
