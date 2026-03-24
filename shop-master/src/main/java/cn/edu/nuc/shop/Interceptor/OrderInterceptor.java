package cn.edu.nuc.shop.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 订单拦截器
 * 拦截需要登录才能访问的请求
 */
public class OrderInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(OrderInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String username = (String) request.getSession().getAttribute("frontuser");

        if (username == null) {
            logger.debug("用户未登录，拦截请求路径: {}", request.getServletPath());
            // 保存当前请求路径，登录后跳转
            request.getSession().setAttribute("orderpath", request.getServletPath());
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false;
        }

        logger.debug("用户已登录，放行请求: {}", username);
        return true;
    }
}
