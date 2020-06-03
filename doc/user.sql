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