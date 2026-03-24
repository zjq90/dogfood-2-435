-- 商城数据库初始化脚本
-- 数据库: myshop

-- 创建数据库
CREATE DATABASE IF NOT EXISTS myshop CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE myshop;

-- 用户表
CREATE TABLE IF NOT EXISTS zk_user (
    uid INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    realname VARCHAR(50) COMMENT '真实姓名',
    email VARCHAR(100) COMMENT '邮箱',
    phone VARCHAR(20) COMMENT '电话',
    addr VARCHAR(200) COMMENT '地址'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 管理员表
CREATE TABLE IF NOT EXISTS zk_admin (
    aid INT PRIMARY KEY AUTO_INCREMENT COMMENT '管理员ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    realname VARCHAR(50) COMMENT '真实姓名',
    email VARCHAR(100) COMMENT '邮箱',
    phone VARCHAR(20) COMMENT '电话'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- 商品表
CREATE TABLE IF NOT EXISTS zk_product (
    pid INT PRIMARY KEY AUTO_INCREMENT COMMENT '商品ID',
    pname VARCHAR(100) NOT NULL COMMENT '商品名称',
    sprice DECIMAL(10,2) DEFAULT 0 COMMENT '销售价',
    cprice DECIMAL(10,2) NOT NULL COMMENT '成本价',
    pic VARCHAR(200) COMMENT '图片',
    pdesc TEXT COMMENT '描述',
    is_hot TINYINT DEFAULT 0 COMMENT '是否热门: 0-否, 1-是',
    pdate DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '上架时间',
    number INT DEFAULT 0 COMMENT '库存数量'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- 订单表
CREATE TABLE IF NOT EXISTS zk_forder (
    fid INT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    uid INT NOT NULL COMMENT '用户ID',
    name VARCHAR(50) COMMENT '收货人姓名',
    phone VARCHAR(20) COMMENT '收货人电话',
    addr VARCHAR(200) COMMENT '收货地址',
    total DECIMAL(10,2) DEFAULT 0 COMMENT '订单总金额',
    status TINYINT DEFAULT 0 COMMENT '状态: 0-待发货, 1-已发货, 2-已完成, 3-已取消',
    fdate DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
    FOREIGN KEY (uid) REFERENCES zk_user(uid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- 订单项表
CREATE TABLE IF NOT EXISTS zk_sorder (
    sid INT PRIMARY KEY AUTO_INCREMENT COMMENT '订单项ID',
    fid INT NOT NULL COMMENT '订单ID',
    pid INT NOT NULL COMMENT '商品ID',
    pname VARCHAR(100) COMMENT '商品名称',
    price DECIMAL(10,2) NOT NULL COMMENT '单价',
    number INT NOT NULL DEFAULT 1 COMMENT '数量',
    FOREIGN KEY (fid) REFERENCES zk_forder(fid) ON DELETE CASCADE,
    FOREIGN KEY (pid) REFERENCES zk_product(pid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单项表';

-- 插入默认管理员数据 (密码: admin123, MD5加密)
INSERT INTO zk_admin (username, password, realname, email, phone) VALUES
('admin', 'e10adc3949ba59abbe56e057f20f883e', '系统管理员', 'admin@shop.com', '13800138000');

-- 插入测试用户数据 (密码: 123456, MD5加密)
INSERT INTO zk_user (username, password, realname, email, phone, addr) VALUES
('test', 'e10adc3949ba59abbe56e057f20f883e', '测试用户', 'test@example.com', '13900139000', '北京市朝阳区测试地址');

-- 插入测试商品数据
INSERT INTO zk_product (pname, sprice, cprice, pic, pdesc, is_hot, number) VALUES
('iPhone 15 Pro', 8999.00, 7999.00, 'iphone15.jpg', '苹果最新旗舰手机，钛金属设计', 1, 100),
('MacBook Pro 14', 16999.00, 14999.00, 'macbook14.jpg', '专业级笔记本电脑，M3芯片', 1, 50),
('AirPods Pro 2', 2199.00, 1899.00, 'airpods.jpg', '主动降噪无线耳机', 1, 200),
('iPad Air 5', 5299.00, 4799.00, 'ipadair.jpg', '轻薄便携平板电脑', 0, 80),
('Apple Watch S9', 3499.00, 2999.00, 'watch.jpg', '智能手表，健康监测', 0, 120),
('华为Mate 60 Pro', 7999.00, 6999.00, 'mate60.jpg', '华为旗舰手机，卫星通信', 1, 60),
('小米14 Pro', 5999.00, 4999.00, 'mi14.jpg', '徕卡影像，骁龙8 Gen3', 1, 150),
('索尼WH-1000XM5', 2999.00, 2499.00, 'sony.jpg', '顶级降噪头戴耳机', 0, 70),
('戴尔XPS 13', 9999.00, 8999.00, 'xps13.jpg', '轻薄商务笔记本', 0, 40),
('任天堂Switch', 2499.00, 2099.00, 'switch.jpg', '便携式游戏主机', 1, 90);
