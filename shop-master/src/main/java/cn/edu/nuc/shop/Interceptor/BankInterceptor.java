package cn.edu.nuc.shop.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台拦截器
 * 拦截后台管理请求，需要管理员登录
 */
public class BankInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(BankInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String username = (String) request.getSession().getAttribute("adminusername");

        if (username == null) {
            logger.debug("管理员未登录，拦截请求路径: {}", request.getServletPath());
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return false;
        }

        logger.debug("管理员已登录，放行请求: {}", username);
        return true;
    }
}
