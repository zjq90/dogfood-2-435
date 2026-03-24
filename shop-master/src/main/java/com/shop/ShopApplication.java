package com.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring Boot应用启动类
 * 
 * @author shop
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.shop.mapper")
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
        System.out.println("==========================================");
        System.out.println("商城系统启动成功！");
        System.out.println("前台地址: http://localhost:8080/");
        System.out.println("后台地址: http://localhost:8080/admin/login");
        System.out.println("Druid监控: http://localhost:8080/druid/");
        System.out.println("==========================================");
    }

}
