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

 Date: 02/05/2018 22:59:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_item_param
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_param`;
CREATE TABLE `tb_item_param` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_cat_id` bigint(20) DEFAULT NULL COMMENT '商品类目ID',
  `param_data` text COMMENT '参数数据，格式为json格式',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `item_cat_id` (`item_cat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='商品规则参数';

-- ----------------------------
-- Records of tb_item_param
-- ----------------------------
BEGIN;
INSERT INTO `tb_item_param` VALUES (1, 3, '[{\"group\":\"组名1\",\"params\":[\"组员1\",\"组员2\"]},{\"group\":\"组名2\",\"params\":[\"组员1\",\"组员2\"]},{\"group\":\"组名3\",\"params\":[\"组员1\",\"组员2\",\"组员3\",\"组员4\"]}]', '2015-04-03 10:21:22', '2015-04-03 10:21:22');
INSERT INTO `tb_item_param` VALUES (2, 560, '[{\"group\":\"主体\",\"params\":[\"品牌\",\"型号\",\"颜色\",\"上市年份\"]},{\"group\":\"网络\",\"params\":[\"4G网络制式\",\"3G网络制式\",\"2G网络制式\"]},{\"group\":\"存储\",\"params\":[\"机身内存\",\"储存卡类型\"]}]', '2015-04-03 10:40:12', '2015-04-03 10:40:12');
INSERT INTO `tb_item_param` VALUES (3, 298, '[{\"group\":\"g1\",\"params\":[\"aa\",\"bb\",\"cc\"]},{\"group\":\"g2\",\"params\":[\"ad\",\"sd\"]},{\"group\":\"g3\",\"params\":[\"sdd\",\"sdfs\",\"dfg\"]}]', '2015-06-05 11:59:45', '2015-06-05 11:59:45');
INSERT INTO `tb_item_param` VALUES (21, 440, '[{\"group\":\"1\",\"params\":[\"2w\"]},{\"group\":\"2\",\"params\":[\"1\"]},{\"group\":\"3\",\"params\":[\"1\"]},{\"group\":\"4\",\"params\":[\"1\"]}]', '2015-06-05 12:04:41', '2015-06-05 12:04:41');
INSERT INTO `tb_item_param` VALUES (22, 298, '[{\"group\":\"f1\",\"params\":[\"1\"]},{\"group\":\"f2\",\"params\":[\"2\"]}]', '2015-06-05 12:08:07', '2015-06-05 12:08:07');
INSERT INTO `tb_item_param` VALUES (23, 257, '[{\"group\":\"12\",\"params\":[\"12\"]}]', '2015-06-05 12:10:45', '2015-06-05 12:10:45');
INSERT INTO `tb_item_param` VALUES (24, 443, '[{\"group\":\"股氯气\",\"params\":[\"撒旦法\"]}]', '2015-06-05 12:11:16', '2015-06-05 12:11:16');
INSERT INTO `tb_item_param` VALUES (25, 298, '[{\"group\":\"1\",\"params\":[\"1\"]}]', '2015-06-05 12:21:01', '2015-06-05 12:21:01');
INSERT INTO `tb_item_param` VALUES (26, 582, '[{\"group\":\"分组1\",\"params\":[\"参数1\",\"参数2\",\"参数3\",\"参数4\",\"参数5\"]},{\"group\":\"分组2\",\"params\":[\"参数21\",\"参数22\",\"参数23\",\"参数24\"]}]', '2015-07-23 16:44:32', '2015-07-23 16:44:32');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
