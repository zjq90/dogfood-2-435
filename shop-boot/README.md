# 商城系统 - Spring Boot 重构版

## 项目概述

本项目是将原有的 Spring MVC 网上商城系统重构为基于 Spring Boot + Maven + MyBatis 的现代化 Web 应用。

## 技术栈

- **后端框架**: Spring Boot 2.7.18
- **构建工具**: Maven 3.6+
- **ORM框架**: MyBatis + MyBatis-Plus
- **数据库连接池**: Druid
- **模板引擎**: JSP + JSTL
- **数据库**: MySQL 5.7+
- **日志框架**: SLF4J + Logback
- **工具库**: Lombok, Apache Commons

## 项目结构

```
shop-boot/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── shop/
│   │   │               ├── ShopApplication.java    # 启动类
│   │   │               ├── config/                  # 配置类
│   │   │               ├── controller/              # 控制器层
│   │   │               ├── entity/                  # 实体类
│   │   │               ├── interceptor/             # 拦截器
│   │   │               ├── mapper/                  # MyBatis Mapper
│   │   │               ├── service/                 # 服务层
│   │   │               └── util/                    # 工具类
│   │   ├── resources/
│   │   │   ├── application.yml      # 主配置文件
│   │   │   ├── application-dev.yml  # 开发环境配置
│   │   │   ├── application-prod.yml # 生产环境配置
│   │   │   ├── mapper/              # MyBatis XML映射文件
│   │   │   └── db/
│   │   │       └── schema.sql       # 数据库初始化脚本
│   │   └── webapp/                  # Web应用根目录
│   │       ├── index.jsp            # 首页
│   │       ├── login.jsp            # 登录页
│   │       ├── car.jsp              # 购物车
│   │       ├── order.jsp            # 订单确认
│   │       ├── detail.jsp           # 商品详情
│   │       ├── msg.jsp              # 消息提示
│   │       ├── static/              # 静态资源
│   │       │   ├── css/
│   │       │   ├── js/
│   │       │   └── images/
│   │       └── WEB-INF/
│   │           └── sys/             # 后台管理页面
│   │               ├── login.jsp
│   │               ├── index.jsp
│   │               ├── product/
│   │               ├── order/
│   │               └── user/
│   └── test/                        # 测试代码
├── pom.xml                          # Maven配置
└── README.md                        # 项目说明
```

## 功能模块

### 前台功能
- 用户注册/登录
- 商品浏览（分页展示）
- 商品详情查看
- 购物车管理
- 订单提交
- 个人中心

### 后台功能
- 管理员登录
- 商品管理（增删改查）
- 订单管理（查看、发货、删除）
- 用户管理（查看、删除）
- 数据统计

## 数据库设计

### 主要表结构

1. **t_user** - 用户表
2. **t_admin** - 管理员表
3. **t_product** - 商品表
4. **t_order** - 订单表
5. **t_order_item** - 订单项表

## 快速开始

### 1. 环境要求
- JDK 1.8+
- Maven 3.6+
- MySQL 5.7+

### 2. 数据库初始化
```sql
-- 创建数据库
CREATE DATABASE myshop CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 执行初始化脚本
source src/main/resources/db/schema.sql
```

### 3. 配置文件修改
编辑 `src/main/resources/application.yml`，修改数据库连接信息：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/myshop?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
    username: root
    password: your_password
```

### 4. 编译运行
```bash
# 编译项目
mvn clean compile

# 运行项目
mvn spring-boot:run

# 或者打包后运行
mvn clean package
java -jar target/shop-boot-1.0.0.jar
```

### 5. 访问系统
- 前台首页: http://localhost:8080/
- 后台管理: http://localhost:8080/admin/login

### 默认账号
- **管理员**: admin / admin123
- **测试用户**: test / 123456

## 重构优化点

### 1. 代码结构优化
- 采用 Spring Boot 标准项目结构
- 统一包命名规范
- 分层清晰（Controller/Service/Mapper/Entity）

### 2. 数据库访问优化
- 从 JDBC 迁移到 MyBatis
- 使用 Druid 连接池
- 添加 SQL 日志监控

### 3. 配置管理
- 所有配置集中到 application.yml
- 支持多环境配置（dev/prod）

### 4. 日志完善
- 使用 SLF4J + Logback
- 添加业务操作日志
- 日志分级输出

### 5. 代码简化
- 使用 Lombok 减少样板代码
- 统一异常处理
- 优化 SQL 语句

## 注意事项

1. **图片上传**: 上传的商品图片保存在 `webapp/upload/` 目录下
2. **会话管理**: 使用 HttpSession 存储用户登录状态
3. **事务管理**: 订单提交使用 @Transactional 保证数据一致性

## 许可证

Copyright © 2024 网上商城 版权所有
