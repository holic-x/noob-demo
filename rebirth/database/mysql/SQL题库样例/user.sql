/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.01
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : 127.0.0.01:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 09/09/2023 12:23:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`),
  KEY `idx_age` (`age`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `name`, `age`, `city`) VALUES (1, 'Emma', 20, '深圳');
INSERT INTO `user` (`id`, `name`, `age`, `city`) VALUES (2, 'Liam', 36, '北京');
INSERT INTO `user` (`id`, `name`, `age`, `city`) VALUES (3, 'Noah', 25, '广州');
INSERT INTO `user` (`id`, `name`, `age`, `city`) VALUES (4, 'Jackson', 15, '上海');
INSERT INTO `user` (`id`, `name`, `age`, `city`) VALUES (5, 'Sophia', 41, '北京');
INSERT INTO `user` (`id`, `name`, `age`, `city`) VALUES (6, 'Aiden', 47, '北京');
INSERT INTO `user` (`id`, `name`, `age`, `city`) VALUES (7, 'Isabella', 23, '广州');
INSERT INTO `user` (`id`, `name`, `age`, `city`) VALUES (8, 'Lucas', 17, '深圳');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
