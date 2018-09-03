/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2018-08-05 08:36:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_pri_key_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_pri_key_product`;
CREATE TABLE `tb_pri_key_product` (
  `generator_name` varchar(255) NOT NULL,
  `next_val` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`generator_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_pri_key_product
-- ----------------------------
INSERT INTO `tb_pri_key_product` VALUES ('seq_tb_product_item', '101');
INSERT INTO `tb_pri_key_product` VALUES ('seq_tb_product_type', '51');

-- ----------------------------
-- Table structure for tb_pri_key_system
-- ----------------------------
DROP TABLE IF EXISTS `tb_pri_key_system`;
CREATE TABLE `tb_pri_key_system` (
  `generator_name` varchar(255) NOT NULL,
  `next_val` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`generator_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_pri_key_system
-- ----------------------------
INSERT INTO `tb_pri_key_system` VALUES ('seq_tb_upload_file', '261');

-- ----------------------------
-- Table structure for tb_product_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_item`;
CREATE TABLE `tb_product_item` (
  `id` int(11) NOT NULL,
  `add_ip` varchar(255) DEFAULT NULL,
  `add_time` datetime NOT NULL,
  `is_dele` varchar(255) DEFAULT NULL,
  `update_ip` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `rank` int(5) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `is_new` int(2) DEFAULT NULL,
  `is_hot` int(2) DEFAULT NULL,
  `on_sale` int(2) DEFAULT NULL,
  `is_show` int(2) DEFAULT NULL,
  `discribe` text,
  `item_name` varchar(100) DEFAULT NULL,
  `type_name` varchar(100) DEFAULT NULL,
  `catalogid` varchar(255) DEFAULT NULL,
  `summary` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_item
-- ----------------------------

-- ----------------------------
-- Table structure for tb_product_pic
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_pic`;
CREATE TABLE `tb_product_pic` (
  `id` int(11) NOT NULL,
  `add_ip` varchar(255) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `is_dele` varchar(255) DEFAULT NULL,
  `update_ip` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `file_id` int(11) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_pic
-- ----------------------------

-- ----------------------------
-- Table structure for tb_product_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_type`;
CREATE TABLE `tb_product_type` (
  `id` int(11) NOT NULL,
  `add_ip` varchar(255) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `is_dele` varchar(255) DEFAULT NULL,
  `update_ip` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `has_products` int(11) DEFAULT NULL,
  `is_show` int(11) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `parent_name` varchar(100) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  `type_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_type
-- ----------------------------

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL,
  `add_ip` varchar(255) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `is_dele` varchar(255) DEFAULT NULL,
  `update_ip` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------

-- ----------------------------
-- Table structure for tb_upload_file
-- ----------------------------
DROP TABLE IF EXISTS `tb_upload_file`;
CREATE TABLE `tb_upload_file` (
  `id` int(11) NOT NULL,
  `catalogid` varchar(100) DEFAULT NULL,
  `discription` varchar(100) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `file_type` varchar(100) DEFAULT NULL,
  `is_dele` varchar(10) DEFAULT NULL,
  `kind` varchar(100) DEFAULT NULL,
  `module` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `path` varchar(200) DEFAULT NULL,
  `target_file_name` varchar(100) DEFAULT NULL,
  `upload_file_name` varchar(100) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_upload_file
-- ----------------------------
INSERT INTO `tb_upload_file` VALUES ('251', '76b83d04-ebd5-4e09-990f-865d5d4ab048', null, '94485', 'image/jpeg', '0', 'images', '41', '微信图片_20180802090138.jpg', '/uploadfile/41/images/7/1533388237564.jpg', '1533388237564.jpg', '微信图片_20180802090138.jpg', '0');
INSERT INTO `tb_upload_file` VALUES ('252', '76b83d04-ebd5-4e09-990f-865d5d4ab048', null, '102365', 'image/jpeg', '0', 'images', '41', '微信图片_20180802090140.jpg', '/uploadfile/41/images/7/1533388246037.jpg', '1533388246037.jpg', '微信图片_20180802090140.jpg', '1');
INSERT INTO `tb_upload_file` VALUES ('253', '76b83d04-ebd5-4e09-990f-865d5d4ab048', null, '29601', 'image/jpeg', '0', 'images', '41', '微信图片_20180802090143.jpg', '/uploadfile/41/images/7/1533388246955.jpg', '1533388246955.jpg', '微信图片_20180802090143.jpg', '2');
INSERT INTO `tb_upload_file` VALUES ('254', '76b83d04-ebd5-4e09-990f-865d5d4ab048', null, '121125', 'image/jpeg', '0', 'images', '41', '微信图片_20180802090147.jpg', '/uploadfile/41/images/7/1533388247966.jpg', '1533388247966.jpg', '微信图片_20180802090147.jpg', '3');
INSERT INTO `tb_upload_file` VALUES ('255', '67fac7c0-3f1b-4e6f-8508-29b75d3b6fb4', null, '290952', 'image/jpeg', '0', 'images', '42', '微信图片_20180802090132.jpg', '/uploadfile/42/images/7/1533388289156.jpg', '1533388289156.jpg', '微信图片_20180802090132.jpg', '0');
INSERT INTO `tb_upload_file` VALUES ('256', '67fac7c0-3f1b-4e6f-8508-29b75d3b6fb4', null, '121125', 'image/jpeg', '0', 'images', '42', '微信图片_20180802090147.jpg', '/uploadfile/42/images/7/1533388295844.jpg', '1533388295844.jpg', '微信图片_20180802090147.jpg', '1');
INSERT INTO `tb_upload_file` VALUES ('257', '67fac7c0-3f1b-4e6f-8508-29b75d3b6fb4', null, '146116', 'image/jpeg', '0', 'images', '42', '微信图片_20180802090150.jpg', '/uploadfile/42/images/7/1533388296810.jpg', '1533388296810.jpg', '微信图片_20180802090150.jpg', '2');
INSERT INTO `tb_upload_file` VALUES ('258', '67fac7c0-3f1b-4e6f-8508-29b75d3b6fb4', null, '99990', 'image/jpeg', '0', 'images', '42', '微信图片_20180802090154.jpg', '/uploadfile/42/images/7/1533388297880.jpg', '1533388297880.jpg', '微信图片_20180802090154.jpg', '3');
INSERT INTO `tb_upload_file` VALUES ('259', '67fac7c0-3f1b-4e6f-8508-29b75d3b6fb4', null, '87377', 'image/jpeg', '0', 'images', '42', '微信图片_20180802090226.jpg', '/uploadfile/42/images/7/1533388299065.jpg', '1533388299065.jpg', '微信图片_20180802090226.jpg', '4');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL,
  `add_ip` varchar(255) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `is_dele` varchar(255) DEFAULT NULL,
  `update_ip` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `last_login_ip` varchar(255) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `real_name` varchar(100) DEFAULT NULL,
  `roleids` varchar(255) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('0', null, null, '0', '127.0.0.1', '2018-08-04 12:53:19', '47', '127.0.0.1', '2018-08-04 12:53:19', 'A722DD2BB2180684DA75F979695749B4', '超级管理员', null, 'admin');
