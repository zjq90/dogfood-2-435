package com.shop.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台登录拦截器
 * 检查管理员是否已登录
 * 
 * @author shop
 */
public class AdminInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AdminInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = (String) request.getSession().getAttribute("adminusername");
        
        if (username == null) {
            logger.warn("未登录访问后台管理页面: {}", request.getRequestURI());
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return false;
        }
        
        return true;
    }
}
