package cn.edu.nuc.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 商城应用主启动类
 * Spring Boot应用入口，配置扫描和启动参数
 */
@SpringBootApplication
@MapperScan("cn.edu.nuc.shop.mapper")
@EnableTransactionManagement
public class ShopApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
        System.out.println("===========================================");
        System.out.println("  商城系统启动成功!");
        System.out.println("  访问地址: http://localhost:8080");
        System.out.println("===========================================");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ShopApplication.class);
    }
}
