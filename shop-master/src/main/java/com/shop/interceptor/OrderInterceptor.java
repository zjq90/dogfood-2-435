package com.shop.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 订单拦截器
 * 检查用户是否已登录
 * 
 * @author shop
 */
public class OrderInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(OrderInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = (String) request.getSession().getAttribute("frontuser");

        if (username == null) {
            logger.warn("未登录访问订单页面: {}", request.getRequestURI());
            request.getSession().setAttribute("orderpath", request.getServletPath());
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false;
        }

        return true;
    }
}
