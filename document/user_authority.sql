/*
 Navicat Premium Data Transfer

 Source Server         : 172.28.21.243
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 172.28.21.243:3306
 Source Schema         : user_authority

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 17/02/2020 19:51:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `modify_time` timestamp(0) NULL DEFAULT NULL,
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort_number` int(11) NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 0, 'el-icon-lx-settings', 'setting', '系统管理', NULL, NULL, '2020-01-15 14:05:19', NULL, NULL, 990);
INSERT INTO `menu` VALUES (2, 1, 'pepole', 'userlist', '用户列表', NULL, NULL, '2020-01-15 13:59:26', NULL, NULL, 991);
INSERT INTO `menu` VALUES (3, 1, 'list', 'menulist', '菜单列表', NULL, NULL, '2020-01-15 13:59:48', NULL, NULL, 992);
INSERT INTO `menu` VALUES (4, 1, 'list', 'rolelist', '角色列表', NULL, NULL, '2020-01-15 14:05:14', NULL, NULL, 993);
INSERT INTO `menu` VALUES (5, 0, 'el-icon-lx-mobilefill', 'endPoint', '终端管理', NULL, '2019-08-28 09:46:49', '2020-01-15 14:03:50', NULL, NULL, 200);
INSERT INTO `menu` VALUES (9, 5, NULL, 'settingFile', '配置文件', NULL, '2019-08-28 18:21:26', '2020-01-15 13:59:54', NULL, NULL, 201);
INSERT INTO `menu` VALUES (11, 0, 'el-icon-lx-rankfill', 'statis', '统计数据', NULL, '2019-09-19 16:17:53', '2020-01-15 13:59:38', NULL, NULL, 100);
INSERT INTO `menu` VALUES (12, 11, NULL, 'demo_dashboard', '统计大盘', NULL, NULL, '2020-01-15 13:59:31', NULL, NULL, 101);
INSERT INTO `menu` VALUES (13, 11, NULL, 'demo_version', '版本质量', NULL, NULL, '2020-02-17 16:39:24', NULL, NULL, 102);
INSERT INTO `menu` VALUES (14, 11, NULL, 'demo_endpoint', '终端分析', NULL, NULL, '2020-01-15 14:06:33', NULL, NULL, 103);
INSERT INTO `menu` VALUES (16, 0, 'el-icon-lx-mobilefill', '0', '用机手册', NULL, '2020-01-11 10:09:05', '2020-01-11 10:20:39', NULL, NULL, 300);
INSERT INTO `menu` VALUES (17, 16, NULL, 'helperArticle', '功能介绍管理', NULL, '2020-01-11 10:10:29', '2020-01-11 10:10:29', NULL, NULL, 301);
INSERT INTO `menu` VALUES (18, 16, NULL, 'helperBanner', 'banner管理', NULL, '2020-01-11 10:12:45', '2020-01-11 10:13:35', NULL, NULL, 302);
INSERT INTO `menu` VALUES (19, 16, NULL, 'helperProject', '版本管理', NULL, '2020-01-11 17:02:43', '2020-01-11 17:02:43', NULL, NULL, 303);
INSERT INTO `menu` VALUES (20, 11, NULL, 'PointSearch', '埋点查询', NULL, '2020-01-16 17:20:01', '2020-01-16 17:20:08', NULL, NULL, 104);
INSERT INTO `menu` VALUES (21, 11, NULL, 'pointFieldAnalysisAdmin', 'TAG-FLAG', NULL, '2020-02-12 10:25:57', '2020-02-12 10:27:41', NULL, NULL, 105);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url路径',
  `desc` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `modify_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '/omp/user/**', '用户管理', NULL, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (2, '/omp/menu/**', '菜单管理', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `modify_time` datetime(0) NULL DEFAULT NULL,
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', '管理员', '2019-09-03 16:57:25', '2019-09-03 16:57:25', NULL, NULL);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL,
  `menu_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 198 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (182, 1, 12);
INSERT INTO `role_menu` VALUES (183, 1, 13);
INSERT INTO `role_menu` VALUES (184, 1, 14);
INSERT INTO `role_menu` VALUES (185, 1, 20);
INSERT INTO `role_menu` VALUES (186, 1, 21);
INSERT INTO `role_menu` VALUES (187, 1, 11);
INSERT INTO `role_menu` VALUES (188, 1, 9);
INSERT INTO `role_menu` VALUES (189, 1, 5);
INSERT INTO `role_menu` VALUES (190, 1, 17);
INSERT INTO `role_menu` VALUES (191, 1, 18);
INSERT INTO `role_menu` VALUES (192, 1, 19);
INSERT INTO `role_menu` VALUES (193, 1, 16);
INSERT INTO `role_menu` VALUES (194, 1, 2);
INSERT INTO `role_menu` VALUES (195, 1, 3);
INSERT INTO `role_menu` VALUES (196, 1, 4);
INSERT INTO `role_menu` VALUES (197, 1, 1);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(20) NULL DEFAULT NULL COMMENT '权限id',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `modify_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (2, 1, 2, NULL, NULL, NULL, NULL);
INSERT INTO `role_permission` VALUES (3, 1, 2, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工号',
  `nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `modify_time` datetime(0) NULL DEFAULT NULL,
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `modify_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '1', '管理员', '1234', 1, '2019-07-16 17:53:10', '2019-09-05 15:29:39', NULL, NULL);
INSERT INTO `user` VALUES (3, 'test', '2', '测试账号', '000000', 1, '2019-09-05 15:30:17', '2019-09-05 15:30:17', NULL, NULL);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `role_id` bigint(20) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (2, 1, 1, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
