/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : e3mall

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 02/05/2018 23:00:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码，加密存储',
  `phone` varchar(20) DEFAULT NULL COMMENT '注册手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '注册邮箱',
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `phone` (`phone`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES (1, 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '13488888888', 'aa@a.cn', '2015-04-06 17:03:55', '2015-04-06 17:03:55');
INSERT INTO `tb_user` VALUES (2, 'lisi', '202cb962ac59075b964b07152d234b70', '12344444444', NULL, '2015-06-19 10:02:11', '2015-06-19 10:02:11');
INSERT INTO `tb_user` VALUES (3, 'tidy', '202cb962ac59075b964b07152d234b70', '13600112243', NULL, '2015-07-30 17:26:25', '2015-07-30 17:26:25');
INSERT INTO `tb_user` VALUES (4, 'niuniu', '202cb962ac59075b964b07152d234b70', '15866777744', '', '2015-08-01 11:48:42', '2015-08-01 11:48:42');
INSERT INTO `tb_user` VALUES (5, 'zhangsan1', '96e79218965eb72c92a549dd5a330112', '18817353729', NULL, '2017-09-02 22:56:11', '2017-09-02 22:56:11');
INSERT INTO `tb_user` VALUES (6, 'roc', '96e79218965eb72c92a549dd5a330112', '18817353534', NULL, '2017-09-02 23:27:56', '2017-09-02 23:27:56');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
