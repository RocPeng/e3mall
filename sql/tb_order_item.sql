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

 Date: 02/05/2018 23:00:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_order_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_item`;
CREATE TABLE `tb_order_item` (
  `id` varchar(20) COLLATE utf8_bin NOT NULL,
  `item_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '商品id',
  `order_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '订单id',
  `num` int(10) DEFAULT NULL COMMENT '商品购买数量',
  `title` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '商品标题',
  `price` bigint(50) DEFAULT NULL COMMENT '商品单价',
  `total_fee` bigint(50) DEFAULT NULL COMMENT '商品总金额',
  `pic_path` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '商品图片地址',
  PRIMARY KEY (`id`),
  KEY `item_id` (`item_id`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_order_item
-- ----------------------------
BEGIN;
INSERT INTO `tb_order_item` VALUES ('1', '974401', '100548', 3, '苹果(Apple) iPhone 5s (A1533) 16GB 银色 电信3G手机', 409900, 1229700, 'http://image.e3mall.cn/jd/35c54c88f6f147aa88e788042b70e7c4.jpg');
INSERT INTO `tb_order_item` VALUES ('2', '981822', '100548', 3, '苹果(Apple) iPhone 4s 8GB 黑色 联通3G手机', 199900, 599700, 'http://image.e3mall.cn/jd/cb465f68fb9844cbb62cce45837848ba.jpg');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
