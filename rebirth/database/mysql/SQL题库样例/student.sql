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

 Date: 19/08/2023 18:59:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int NOT NULL,
  `sid` int DEFAULT NULL,
  `classid` int DEFAULT NULL,
  `sname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sid` (`sid`) USING BTREE,
  KEY `idx_classe` (`classid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` (`id`, `sid`, `classid`, `sname`) VALUES (1, 10001, 1, '路飞');
INSERT INTO `student` (`id`, `sid`, `classid`, `sname`) VALUES (2, 10002, 1, '索隆');
INSERT INTO `student` (`id`, `sid`, `classid`, `sname`) VALUES (3, 10003, 1, '山治');
INSERT INTO `student` (`id`, `sid`, `classid`, `sname`) VALUES (4, 10004, 2, '娜美');
INSERT INTO `student` (`id`, `sid`, `classid`, `sname`) VALUES (5, 10005, 2, '乔巴');
INSERT INTO `student` (`id`, `sid`, `classid`, `sname`) VALUES (6, 10006, 3, '罗宾');
INSERT INTO `student` (`id`, `sid`, `classid`, `sname`) VALUES (7, 10007, 3, '布洛克');
INSERT INTO `student` (`id`, `sid`, `classid`, `sname`) VALUES (8, 10008, 4, '乌索普');
INSERT INTO `student` (`id`, `sid`, `classid`, `sname`) VALUES (9, 10009, 1, '路西');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
