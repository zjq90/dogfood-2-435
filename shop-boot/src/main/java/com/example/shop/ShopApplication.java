package com.example.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 商城系统启动类
 * 基于Spring Boot 2.7.x构建
 *
 * @author example
 * @version 1.0.0
 */
@SpringBootApplication
@MapperScan("com.example.shop.mapper")
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
        System.out.println("==============================================");
        System.out.println("商城系统启动成功!");
        System.out.println("访问地址: http://localhost:8080/");
        System.out.println("后台管理: http://localhost:8080/admin/login");
        System.out.println("==============================================");
    }
}
