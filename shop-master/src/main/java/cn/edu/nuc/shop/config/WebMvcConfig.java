package cn.edu.nuc.shop.config;

import cn.edu.nuc.shop.interceptor.BankInterceptor;
import cn.edu.nuc.shop.interceptor.OrderInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC配置类
 * 配置拦截器、静态资源映射、文件上传等
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    /**
     * 配置拦截器
     * @param registry 拦截器注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("配置拦截器");

        // 订单拦截器 - 拦截需要用户登录的请求
        registry.addInterceptor(new OrderInterceptor())
                .addPathPatterns("/forder/**")
                .addPathPatterns("/cart/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/logout");

        // 后台拦截器 - 拦截后台管理请求
        registry.addInterceptor(new BankInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/logout");

        logger.info("拦截器配置完成");
    }

    /**
     * 配置静态资源映射
     * @param registry 资源处理器注册器
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.debug("配置静态资源映射");

        // 配置上传文件的静态资源映射
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/src/main/webapp/upload/");

        // 其他静态资源
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    /**
     * 配置文件上传解析器
     * @return CommonsMultipartResolver
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        logger.debug("配置文件上传解析器");
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        // 设置最大上传大小为10MB
        resolver.setMaxUploadSize(10 * 1024 * 1024);
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }
}
