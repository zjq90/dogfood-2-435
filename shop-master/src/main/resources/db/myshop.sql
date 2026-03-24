/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : myshop

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2024-03-24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for zk_admin
-- ----------------------------
DROP TABLE IF EXISTS `zk_admin`;
CREATE TABLE `zk_admin` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of zk_admin
-- ----------------------------
INSERT INTO `zk_admin` VALUES ('1', 'admin', 'admin');

-- ----------------------------
-- Table structure for zk_user
-- ----------------------------
DROP TABLE IF EXISTS `zk_user`;
CREATE TABLE `zk_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of zk_user
-- ----------------------------
INSERT INTO `zk_user` VALUES ('1', 'user', 'user', NULL, NULL, NULL, NULL);
INSERT INTO `zk_user` VALUES ('4', 'test', 'test', '测试用户', 'test@test.com', '13800138000', '北京市');

-- ----------------------------
-- Table structure for zk_product
-- ----------------------------
DROP TABLE IF EXISTS `zk_product`;
CREATE TABLE `zk_product` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(255) DEFAULT NULL,
  `sprice` double(10,2) DEFAULT NULL,
  `cprice` double(10,2) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `pdesc` varchar(500) DEFAULT NULL,
  `is_hot` tinyint(1) DEFAULT NULL,
  `pdate` datetime DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of zk_product
-- ----------------------------
INSERT INTO `zk_product` VALUES ('1', '苹果手机', '6999.00', '5999.00', NULL, '最新款苹果手机，性能强劲', '1', NOW(), '100');
INSERT INTO `zk_product` VALUES ('2', '华为手机', '4999.00', '3999.00', NULL, '华为旗舰手机，拍照出色', '1', NOW(), '100');
INSERT INTO `zk_product` VALUES ('3', '小米手机', '2999.00', '2499.00', NULL, '小米手机，性价比之王', '0', NOW(), '100');
INSERT INTO `zk_product` VALUES ('4', '联想笔记本', '5999.00', '4999.00', NULL, '联想商务笔记本', '0', NOW(), '50');
INSERT INTO `zk_product` VALUES ('5', '戴尔笔记本', '6999.00', '5999.00', NULL, '戴尔游戏笔记本', '1', NOW(), '50');

-- ----------------------------
-- Table structure for zk_forder
-- ----------------------------
DROP TABLE IF EXISTS `zk_forder`;
CREATE TABLE `zk_forder` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `total` double(10,2) DEFAULT NULL,
  `post` varchar(20) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for zk_sorder
-- ----------------------------
DROP TABLE IF EXISTS `zk_sorder`;
CREATE TABLE `zk_sorder` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `fid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
