package cn.edu.nuc.shop.controller;

import cn.edu.nuc.shop.entity.Admin;
import cn.edu.nuc.shop.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * 管理员控制器
 * 处理管理员登录、登出等请求
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    /**
     * 后台首页
     * @param session Session对象
     * @return 首页路径或登录页面
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpSession session) {
        logger.debug("访问后台首页");

        if (session.getAttribute("adminusername") == null) {
            logger.debug("管理员未登录，跳转到登录页面");
            session.setAttribute("msg", "用户还没有登录");
            return "login";
        }
        return "index";
    }

    /**
     * 跳转到管理员登录页面
     * @return 登录页面路径
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin() {
        logger.debug("跳转到管理员登录页面");
        return "login";
    }

    /**
     * 管理员登录
     * @param admin 管理员信息（用户名、密码）
     * @param session Session对象
     * @return 重定向路径
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Admin admin, HttpSession session) {
        logger.info("管理员登录请求，用户名: {}", admin.getUsername());
        try {
            Admin existAdmin = adminService.login(admin.getUsername(), admin.getPassword());

            session.setAttribute("adminusername", existAdmin.getUsername());
            session.removeAttribute("msg");

            logger.info("管理员登录成功: {}", admin.getUsername());
            return "redirect:/admin/";

        } catch (Exception e) {
            logger.warn("管理员登录失败: {}，原因: {}", admin.getUsername(), e.getMessage());
            session.setAttribute("msg", "用户名或密码错误");
        }
        return "redirect:/admin/login";
    }

    /**
     * 管理员登出
     * @param session Session对象
     * @return 重定向到登录页面
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        String username = (String) session.getAttribute("adminusername");
        logger.info("管理员登出: {}", username);

        session.removeAttribute("adminusername");

        return "redirect:/admin/login";
    }
}

