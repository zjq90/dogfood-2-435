package cn.edu.nuc.shop.controller;

import cn.edu.nuc.shop.entity.User;
import cn.edu.nuc.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * 用户控制器
 * 处理用户登录、注册、登出等请求
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 跳转到用户登录页面
     * @return 登录页面路径
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin() {
        logger.debug("跳转到用户登录页面");
        return "redirect:/login.jsp";
    }

    /**
     * 用户登录
     * @param user 用户信息（用户名、密码）
     * @param session Session对象
     * @return 重定向路径
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, HttpSession session) {
        logger.info("用户登录请求，用户名: {}", user.getUsername());
        try {
            User existUser = userService.login(user.getUsername(), user.getPassword());

            // 登录成功，将用户信息存入Session
            session.setAttribute("frontuser", existUser.getUsername());
            session.setAttribute("frontuserId", existUser.getUid());
            session.removeAttribute("msg");

            logger.info("用户登录成功: {}", user.getUsername());

            // 检查是否有之前保存的请求路径
            String orderPath = (String) session.getAttribute("orderpath");
            if (orderPath != null) {
                session.removeAttribute("orderpath");
                logger.debug("跳转到之前的请求路径: {}", orderPath);
                return "redirect:" + orderPath;
            }

            return "redirect:/product/frontlist";

        } catch (Exception e) {
            logger.warn("用户登录失败: {}，原因: {}", user.getUsername(), e.getMessage());
            session.setAttribute("msg", "用户名或密码错误");
        }
        return "forward:/login.jsp";
    }

    /**
     * 用户登出
     * @param session Session对象
     * @return 重定向到登录页面
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        String username = (String) session.getAttribute("frontuser");
        logger.info("用户登出: {}", username);

        session.removeAttribute("frontuser");
        session.removeAttribute("frontuserId");

        return "redirect:/login.jsp";
    }
}
