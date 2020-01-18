/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80015
Source Host           : 127.0.0.1:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2020-01-18 16:34:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `publish_username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '发表用户',
  `category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文章分类',
  `title` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '内容',
  `summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '内容摘要',
  `page_view` int(11) DEFAULT '0' COMMENT '浏览量',
  `like_count` int(11) DEFAULT '0' COMMENT '点赞数',
  `comment_count` int(11) DEFAULT '0' COMMENT '评论总数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `state` int(1) DEFAULT NULL COMMENT '0表示草稿箱，1表示已发表，2表示已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客文章';

-- ----------------------------
-- Records of blog_article
-- ----------------------------

-- ----------------------------
-- Table structure for blog_article_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_category`;
CREATE TABLE `blog_article_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) DEFAULT NULL COMMENT '文章id',
  `category_id` bigint(20) DEFAULT NULL COMMENT '分类id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章分类关联表';

-- ----------------------------
-- Records of blog_article_category
-- ----------------------------

-- ----------------------------
-- Table structure for blog_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_tag`;
CREATE TABLE `blog_article_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) DEFAULT NULL COMMENT '文章id',
  `tag_id` bigint(20) DEFAULT NULL COMMENT '标签id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章标签关联表';

-- ----------------------------
-- Records of blog_article_tag
-- ----------------------------

-- ----------------------------
-- Table structure for blog_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '分类名称',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章分类表';

-- ----------------------------
-- Records of blog_category
-- ----------------------------

-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `publish_username` varchar(64) DEFAULT NULL COMMENT '发表用户',
  `article_id` bigint(20) DEFAULT NULL COMMENT '文章id',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父评论id',
  `like_count` int(12) DEFAULT '0' COMMENT '点赞数',
  `content` text COMMENT '评论内容',
  `comment_time` datetime DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1218429512315559938 DEFAULT CHARSET=utf8 COMMENT='评论信息';

-- ----------------------------
-- Records of blog_comment
-- ----------------------------
INSERT INTO `blog_comment` VALUES ('1218076310936211458', 'admin', null, null, '11', 'test', '2020-01-17 17:17:29');
INSERT INTO `blog_comment` VALUES ('1218076633813733378', 'admin', null, null, '11', 'test', '2020-01-17 17:17:29');
INSERT INTO `blog_comment` VALUES ('1218077127885967361', 'admin', null, null, '11', 'test', '2020-01-17 17:17:29');
INSERT INTO `blog_comment` VALUES ('1218087955867623426', 'admin', null, null, '11', 'test', '2020-01-17 17:17:29');
INSERT INTO `blog_comment` VALUES ('1218088366473207809', 'admin', null, null, '11', 'test', '2020-01-17 17:17:29');
INSERT INTO `blog_comment` VALUES ('1218089207091367937', 'admin', null, null, '12', 'test', '2020-01-17 17:17:29');
INSERT INTO `blog_comment` VALUES ('1218100035664416769', 'admin', null, null, '13', 'test', '2020-01-17 17:17:29');
INSERT INTO `blog_comment` VALUES ('1218429512315559937', 'admin', '111', null, '1', 'test11', '2020-01-18 15:06:48');

-- ----------------------------
-- Table structure for blog_like_count
-- ----------------------------
DROP TABLE IF EXISTS `blog_like_count`;
CREATE TABLE `blog_like_count` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `article_id` bigint(20) DEFAULT NULL COMMENT '文章id',
  `comment_id` bigint(20) DEFAULT NULL COMMENT '评论id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1218417347881852931 DEFAULT CHARSET=utf8 COMMENT='点赞记录表';

-- ----------------------------
-- Records of blog_like_count
-- ----------------------------
INSERT INTO `blog_like_count` VALUES ('1218380764227035138', 'admin', null, '1218100035664416769', '2020-01-18 11:53:05');
INSERT INTO `blog_like_count` VALUES ('1218417347881852930', 'admin', null, '1218089207091367937', '2020-01-18 14:18:27');

-- ----------------------------
-- Table structure for blog_permission
-- ----------------------------
DROP TABLE IF EXISTS `blog_permission`;
CREATE TABLE `blog_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级权限id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `value` varchar(12) DEFAULT NULL COMMENT '权限值',
  `type` int(1) DEFAULT NULL COMMENT '权限类型：0->目录, 1->菜单, 2->按钮（接口绑定权限)',
  `status` int(1) DEFAULT NULL COMMENT '启用状态: 0->禁用, 1->启用',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1217337611382370306 DEFAULT CHARSET=utf8 COMMENT='用户权限表';

-- ----------------------------
-- Records of blog_permission
-- ----------------------------
INSERT INTO `blog_permission` VALUES ('1217337262072344577', null, '浏览', '1', '1', '1', '1');
INSERT INTO `blog_permission` VALUES ('1217337611382370305', null, '修改', '2', '1', '1', '1');

-- ----------------------------
-- Table structure for blog_role
-- ----------------------------
DROP TABLE IF EXISTS `blog_role`;
CREATE TABLE `blog_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `description` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用, 1->启用',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1217288338112471042 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of blog_role
-- ----------------------------
INSERT INTO `blog_role` VALUES ('1216914681506701315', '超级管理员', '超级管理员', '1', '0');
INSERT INTO `blog_role` VALUES ('1217288338112471041', '普通访客', '普通访客', '1', '1');

-- ----------------------------
-- Table structure for blog_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `blog_role_permission`;
CREATE TABLE `blog_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(20) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1217371732502634499 DEFAULT CHARSET=utf8 COMMENT='用户角色与权限关联表';

-- ----------------------------
-- Records of blog_role_permission
-- ----------------------------
INSERT INTO `blog_role_permission` VALUES ('1217371732502634497', '1216914681506701315', '1217337611382370305');
INSERT INTO `blog_role_permission` VALUES ('1217371732502634498', '1216914681506701315', '1217337262072344577');

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '标签名称',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章标签';

-- ----------------------------
-- Records of blog_tag
-- ----------------------------

-- ----------------------------
-- Table structure for blog_user
-- ----------------------------
DROP TABLE IF EXISTS `blog_user`;
CREATE TABLE `blog_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '用户密码',
  `nick_name` varchar(200) DEFAULT NULL COMMENT '用户昵称',
  `telephone` int(11) DEFAULT NULL COMMENT '联系电话',
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `login_last_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` int(1) DEFAULT '1' COMMENT '账号启用状态：0->禁用, 1->启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1217719538782253059 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of blog_user
-- ----------------------------
INSERT INTO `blog_user` VALUES ('1214832163097874434', 'admin', '$2a$10$fYwd5YCIDbluhCdzcOfsSOHJRGh.4kT13Q4tb6mwXxbhs0MRx7eE2', 'monster', null, '', 'example@gmail.com', '', '2020-01-08 16:52:13', '2020-01-16 16:03:31', '1');
INSERT INTO `blog_user` VALUES ('1214832908677287937', 'root', '$2a$10$YraoFhdatAeiRSqcfytf6.ltnkPHF.duMPqjc.E.z.XY.26n/j3Da', 'monster', null, '', 'example@gmail.com', '', '2020-01-08 16:55:11', '2020-01-15 14:17:53', '1');
INSERT INTO `blog_user` VALUES ('1215466527175344129', 'ro', '$2a$10$vufirxApfC0qnsps4bU2cunE2MxoXBho59rSCpg0W3Gz5ZNNnCHia', 'monster', null, '', 'example@gmail.com', '', '2020-01-10 10:52:57', '2020-01-15 14:17:53', '1');
INSERT INTO `blog_user` VALUES ('1217326964745670657', 'test', '$2a$10$.bNf5q6JJeCcN39cOqADZe9qvB.BAduD.yq0nlV7qLY0SHVVCYSUW', 'test', null, '', 'example@gmail.com', 'teat', '2020-01-15 14:05:40', '2020-01-15 14:42:48', '1');
INSERT INTO `blog_user` VALUES ('1217719538782253058', 'test2', '$2a$10$w.lNtbP4wPzJNDvS6LK/tO4adVGfH.to7KsNB72FVT0sHI5T7WLZ.', 'test2', null, '', 'example@gmail.com', 'test2', '2020-01-16 16:05:37', null, '1');

-- ----------------------------
-- Table structure for blog_user_role
-- ----------------------------
DROP TABLE IF EXISTS `blog_user_role`;
CREATE TABLE `blog_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1217290136386732035 DEFAULT CHARSET=utf8 COMMENT='用户与角色关联表';

-- ----------------------------
-- Records of blog_user_role
-- ----------------------------
INSERT INTO `blog_user_role` VALUES ('1217290136386732034', '1214832163097874434', '1216914681506701315');
