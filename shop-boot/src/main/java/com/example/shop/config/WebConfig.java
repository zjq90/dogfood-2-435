package com.example.shop.config;

import com.example.shop.interceptor.AdminInterceptor;
import com.example.shop.interceptor.OrderInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置类
 * 配置拦截器、资源处理器等
 *
 * @author example
 * @version 1.0.0
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final OrderInterceptor orderInterceptor;
    private final AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 订单提交拦截器 - 需要登录
        registry.addInterceptor(orderInterceptor)
                .addPathPatterns("/forder/order")
                .excludePathPatterns("/user/login", "/user/logout", "/static/**");

        // 后台管理拦截器 - 需要管理员登录
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/admin/**", "/product/list", "/product/add", "/product/edit", "/product/delete")
                .excludePathPatterns("/admin/login", "/admin/logout", "/product/frontlist", "/product/detail", "/static/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 静态资源映射
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        // 上传文件映射
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:upload/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 默认首页
        registry.addViewController("/").setViewName("forward:/product/frontlist");
    }
}
