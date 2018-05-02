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

 Date: 02/05/2018 23:00:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_item_param_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_param_item`;
CREATE TABLE `tb_item_param_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `param_data` text COMMENT '参数数据，格式为json格式',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `item_id` (`item_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='商品规格和商品的关系表';

-- ----------------------------
-- Records of tb_item_param_item
-- ----------------------------
BEGIN;
INSERT INTO `tb_item_param_item` VALUES (1, 48, '[{\"group\":\"主体\",\"params\":[{\"k\":\"品牌\",\"v\":\"苹果（Apple）\"},{\"k\":\"型号\",\"v\":\"iPhone 6 A1586\"},{\"k\":\"颜色\",\"v\":\"金色\"},{\"k\":\"上市年份\",\"v\":\"2014\"}]},{\"group\":\"网络\",\"params\":[{\"k\":\"4G网络制式\",\"v\":\"移动4G(TD-LTE)/联通4G(FDD-LTE)/电信4G(FDD-LTE)\"},{\"k\":\"3G网络制式\",\"v\":\"移动3G(TD-SCDMA)/联通3G(WCDMA)/电信3G（CDMA2000）\"},{\"k\":\"2G网络制式\",\"v\":\"移动2G/联通2G(GSM)/电信2G(CDMA)\"}]},{\"group\":\"存储\",\"params\":[{\"k\":\"机身内存\",\"v\":\"16GB ROM\"},{\"k\":\"储存卡类型\",\"v\":\"不支持\"}]}]', '2015-04-03 10:52:55', '2015-04-03 10:52:55');
INSERT INTO `tb_item_param_item` VALUES (2, 1188043, '[{\"group\":\"主体\",\"params\":[{\"k\":\"品牌\",\"v\":\"锤子\"},{\"k\":\"型号\",\"v\":\"T1(SM705)\"},{\"k\":\"颜色\",\"v\":\"黑色\"},{\"k\":\"上市年份\",\"v\":\"2014年\"}]},{\"group\":\"网络\",\"params\":[{\"k\":\"4G网络制式\",\"v\":\"移动4G（TD-LTE）/联通4G（FDD-LTE）\"},{\"k\":\"3G网络制式\",\"v\":\"移动3G(TD-SCDMA)/联通3G(WCDMA)\"},{\"k\":\"2G网络制式\",\"v\":\"移动2G/联通2G(GSM)\"}]},{\"group\":\"存储\",\"params\":[{\"k\":\"机身内存\",\"v\":\"32GB ROM\"},{\"k\":\"储存卡类型\",\"v\":\"2GB RAM\"}]}]', '2015-04-06 11:24:10', '2015-04-06 11:24:10');
INSERT INTO `tb_item_param_item` VALUES (3, 1433500495290, '[{\"group\":\"主体\",\"params\":[{\"k\":\"品牌\",\"v\":\"1\"},{\"k\":\"型号\",\"v\":\"2\"},{\"k\":\"颜色\",\"v\":\"3\"},{\"k\":\"上市年份\",\"v\":\"4\"}]},{\"group\":\"网络\",\"params\":[{\"k\":\"4G网络制式\",\"v\":\"a\"},{\"k\":\"3G网络制式\",\"v\":\"b\"},{\"k\":\"2G网络制式\",\"v\":\"c\"}]},{\"group\":\"存储\",\"params\":[{\"k\":\"机身内存\",\"v\":\"de\"},{\"k\":\"储存卡类型\",\"v\":\"ef\"}]}]', '2015-06-05 18:34:52', '2015-06-05 18:34:52');
INSERT INTO `tb_item_param_item` VALUES (4, 1001434271015869, '[{\"group\":\"主体\",\"params\":[{\"k\":\"品牌\",\"v\":\"华为（HUAWEI）\"},{\"k\":\"型号\",\"v\":\"P8\"},{\"k\":\"颜色\",\"v\":\"皓月银\"},{\"k\":\"上市年份\",\"v\":\"2015年\"}]},{\"group\":\"网络\",\"params\":[{\"k\":\"4G网络制式\",\"v\":\"移动4G(TDD-LTE)/联通4G(TDD-LTE/FDD-LTE)\"},{\"k\":\"3G网络制式\",\"v\":\"移动3G(TD-SCDMA)/联通3G(WCDMA)\"},{\"k\":\"2G网络制式\",\"v\":\"移动2G/联通2G(GSM)\"}]},{\"group\":\"存储\",\"params\":[{\"k\":\"机身内存\",\"v\":\"16GB ROM\"},{\"k\":\"储存卡类型\",\"v\":\"MicroSD(TF)\"}]}]', '2015-06-14 16:36:55', '2015-06-14 16:36:55');
INSERT INTO `tb_item_param_item` VALUES (5, 101434521126763, '[{\"group\":\"组名1\",\"params\":[{\"k\":\"组员1\",\"v\":\"a\"},{\"k\":\"组员2\",\"v\":\"a\"}]},{\"group\":\"组名2\",\"params\":[{\"k\":\"组员1\",\"v\":\"sd\"},{\"k\":\"组员2\",\"v\":\"ss\"}]},{\"group\":\"组名3\",\"params\":[{\"k\":\"组员1\",\"v\":\"sd\"},{\"k\":\"组员2\",\"v\":\"sd\"},{\"k\":\"组员3\",\"v\":\"sd\"},{\"k\":\"组员4\",\"v\":\"sda\"}]}]', '2015-06-17 14:05:26', '2015-06-17 14:05:26');
INSERT INTO `tb_item_param_item` VALUES (8, 143771131488369, '[{\"group\":\"主体\",\"params\":[{\"k\":\"品牌\",\"v\":\"1\"},{\"k\":\"型号\",\"v\":\"1\"},{\"k\":\"颜色\",\"v\":\"2\"},{\"k\":\"上市年份\",\"v\":\"3\"}]},{\"group\":\"网络\",\"params\":[{\"k\":\"4G网络制式\",\"v\":\"1\"},{\"k\":\"3G网络制式\",\"v\":\"2\"},{\"k\":\"2G网络制式\",\"v\":\"3\"}]},{\"group\":\"存储\",\"params\":[{\"k\":\"机身内存\",\"v\":\"4\"},{\"k\":\"储存卡类型\",\"v\":\"2\"}]}]', '2015-07-24 12:15:14', '2015-07-24 12:15:14');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
