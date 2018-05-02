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

 Date: 02/05/2018 22:57:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_content
-- ----------------------------
DROP TABLE IF EXISTS `tb_content`;
CREATE TABLE `tb_content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) NOT NULL COMMENT '内容类目ID',
  `title` varchar(200) DEFAULT NULL COMMENT '内容标题',
  `sub_title` varchar(100) DEFAULT NULL COMMENT '子标题',
  `title_desc` varchar(500) DEFAULT NULL COMMENT '标题描述',
  `url` varchar(500) DEFAULT NULL COMMENT '链接',
  `pic` varchar(300) DEFAULT NULL COMMENT '图片绝对路径',
  `pic2` varchar(300) DEFAULT NULL COMMENT '图片2',
  `content` text COMMENT '内容',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  KEY `updated` (`updated`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_content
-- ----------------------------
BEGIN;
INSERT INTO `tb_content` VALUES (28, 89, '标题1', '标题1', '标题1', 'http://www.jd.com', 'http://localhost:9000/images/2015/07/27/1437979301511057.jpg', NULL, '标题1', '2015-07-27 14:41:57', '2015-07-27 14:41:57');
INSERT INTO `tb_content` VALUES (29, 89, 'ad2', 'ad2', 'ad2', 'http://www.baidu.com', 'http://localhost:9000/images/2015/07/27/1437979349040954.jpg', NULL, 'ad2', '2015-07-27 14:42:36', '2015-07-27 14:42:36');
INSERT INTO `tb_content` VALUES (31, 89, 'ad4', 'ad4', 'ad4', 'ad4', 'http://localhost:9000/images/2015/07/27/1437979392186756.jpg', NULL, 'ad4', '2015-07-27 14:43:15', '2015-07-27 14:43:15');
INSERT INTO `tb_content` VALUES (32, 90, '小广告111wrw3r', '111dd', '111fsaf', '111', '', '', '111', '2017-08-30 19:32:57', '2017-08-30 19:32:57');
INSERT INTO `tb_content` VALUES (35, 90, '221', '222', '223', '22', '', '', '22', '2017-08-30 19:56:35', '2017-08-30 19:56:35');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
