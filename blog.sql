/*
 Navicat MySQL Data Transfer

 Source Server         : alicloud
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 8.130.94.28:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 03/04/2023 23:28:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `context` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `author` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` date NOT NULL,
  `status` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  `love` int(11) NULL DEFAULT NULL,
  `watch` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_article_tag`(`tag_id`) USING BTREE,
  INDEX `fk_article_type`(`type_id`) USING BTREE,
  CONSTRAINT `fk_article_type` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_article_tag` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `context` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `article_id` int(11) NOT NULL,
  `parent_id` int(11) NOT NULL DEFAULT 0,
  `top_id` int(11) NOT NULL DEFAULT 0,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_comment_article`(`article_id`) USING BTREE,
  CONSTRAINT `fk_comment_article` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
