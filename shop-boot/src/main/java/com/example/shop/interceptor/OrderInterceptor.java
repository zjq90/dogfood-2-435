package com.example.shop.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 订单拦截器
 * 拦截订单相关请求,检查用户是否已登录
 *
 * @author example
 * @version 1.0.0
 */
@Slf4j
@Component
public class OrderInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("frontUser");

        if (username == null) {
            log.warn("未登录用户尝试访问订单页面, IP={}", getClientIp(request));
            // 保存当前请求路径,登录后跳转回来
            session.setAttribute("orderPath", request.getRequestURI());
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false;
        }

        log.debug("用户[{}]访问订单页面", username);
        return true;
    }

    /**
     * 获取客户端IP地址
     */
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
