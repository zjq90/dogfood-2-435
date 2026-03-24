package com.shop.config;

import com.shop.interceptor.AdminInterceptor;
import com.shop.interceptor.OrderInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC配置类
 * 配置拦截器和静态资源
 * 
 * @author shop
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new OrderInterceptor())
                .addPathPatterns("/forder/order")
                .order(1);

        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/product/**")
                .excludePathPatterns("/product/frontlist", "/product/detail")
                .addPathPatterns("/order/**")
                .order(2);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:./upload/");

        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");

        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/");

        registry.addResourceHandler("/image/**")
                .addResourceLocations("classpath:/static/image/");

        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/");

        registry.addResourceHandler("/bank_img/**")
                .addResourceLocations("classpath:/static/bank_img/");

        registry.addResourceHandler("/jslib/**")
                .addResourceLocations("classpath:/static/jslib/");
    }
}
