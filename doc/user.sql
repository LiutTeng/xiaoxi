/*
Navicat MySQL Data Transfer

Source Server         : BaiduYun
Source Server Version : 50722
Source Host           : 106.13.181.6:3306
Source Database       : demo0

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-05-29 11:24:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user0`
-- ----------------------------
DROP TABLE IF EXISTS `user0`;
CREATE TABLE `user0` (
                         `id` bigint(20) NOT NULL COMMENT '主键',
                         `name` varchar(20) NOT NULL COMMENT '姓名',
                         `sex` varchar(10) NOT NULL COMMENT '性别',
                         `phone` varchar(64) NOT NULL COMMENT '手机号（加密）',
                         `create_time` datetime NOT NULL COMMENT '创建时间',
                         `enable` tinyint(1) NOT NULL COMMENT '是否有效（1-有效，0-无效）',
                         `version` bigint(20) NOT NULL COMMENT '版本号',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `user1`
-- ----------------------------
DROP TABLE IF EXISTS `user1`;
CREATE TABLE `user1` (
                         `id` bigint(20) NOT NULL COMMENT '主键',
                         `name` varchar(20) NOT NULL COMMENT '姓名',
                         `sex` varchar(10) NOT NULL COMMENT '性别',
                         `phone` varchar(64) NOT NULL COMMENT '手机号（加密）',
                         `create_time` datetime NOT NULL COMMENT '创建时间',
                         `enable` tinyint(1) NOT NULL COMMENT '是否有效（1-有效，0-无效）',
                         `version` bigint(20) NOT NULL COMMENT '版本号',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;