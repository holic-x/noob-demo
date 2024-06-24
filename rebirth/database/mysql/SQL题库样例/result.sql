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

 Date: 19/08/2023 18:59:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for result
-- ----------------------------
DROP TABLE IF EXISTS `result`;
CREATE TABLE `result` (
  `id` int NOT NULL,
  `sid` int DEFAULT NULL,
  `courseid` int DEFAULT NULL,
  `cname` varchar(255) DEFAULT NULL,
  `score` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sid` (`sid`) USING BTREE,
  KEY `idx_score` (`score`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of result
-- ----------------------------
BEGIN;
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (1, 10001, 1, '语文', 75);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (2, 10001, 2, '数学', 60);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (3, 10001, 3, '英语', 20);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (4, 10002, 1, '语文', 80);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (5, 10002, 2, '数学', 30);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (6, 10002, 3, '英语', 50);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (7, 10003, 1, '语文', 90);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (8, 10003, 2, '数学', 80);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (9, 10003, 3, '英语', 96);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (10, 10004, 1, '语文', 89);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (11, 10004, 2, '数学', 88);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (12, 10004, 3, '英语', 78);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (13, 10005, 1, '语文', 84);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (14, 10005, 2, '数学', 96);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (15, 10005, 3, '英语', 88);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (16, 10006, 1, '语文', 78);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (17, 10006, 2, '数学', 67);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (18, 10006, 3, '英语', 86);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (19, 10007, 1, '语文', 81);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (20, 10007, 2, '数学', 88);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (21, 10007, 3, '英语', 92);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (22, 10008, 1, '语文', 97);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (23, 10008, 2, '数学', 88);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (24, 10008, 3, '英语', 96);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (25, 10009, 1, '语文', 99);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (26, 10009, 2, '数学', 99);
INSERT INTO `result` (`id`, `sid`, `courseid`, `cname`, `score`) VALUES (27, 10009, 3, '英语', 99);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
