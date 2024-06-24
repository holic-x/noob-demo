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

 Date: 11/09/2023 20:16:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` int NOT NULL,
  `student_id` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `course_id` int DEFAULT NULL,
  `course_score` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_id_stuid` (`id`,`student_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_student
-- ----------------------------
BEGIN;
INSERT INTO `t_student` (`id`, `student_id`, `name`, `course_id`, `course_score`) VALUES (2, 1001, 'jj', 2, '88');
INSERT INTO `t_student` (`id`, `student_id`, `name`, `course_id`, `course_score`) VALUES (3, 1001, 'jj', 3, '92');
INSERT INTO `t_student` (`id`, `student_id`, `name`, `course_id`, `course_score`) VALUES (4, 1001, 'jj', 4, '98');
INSERT INTO `t_student` (`id`, `student_id`, `name`, `course_id`, `course_score`) VALUES (5, 1002, 'nn', 1, '88');
INSERT INTO `t_student` (`id`, `student_id`, `name`, `course_id`, `course_score`) VALUES (6, 1002, 'nn', 2, '90');
INSERT INTO `t_student` (`id`, `student_id`, `name`, `course_id`, `course_score`) VALUES (7, 1002, 'nn', 3, '100');
INSERT INTO `t_student` (`id`, `student_id`, `name`, `course_id`, `course_score`) VALUES (8, 1003, 'tt', 1, '66');
INSERT INTO `t_student` (`id`, `student_id`, `name`, `course_id`, `course_score`) VALUES (9, 1003, 'tt', 2, '45');
INSERT INTO `t_student` (`id`, `student_id`, `name`, `course_id`, `course_score`) VALUES (10, 1003, 'tt', 3, '55');
INSERT INTO `t_student` (`id`, `student_id`, `name`, `course_id`, `course_score`) VALUES (11, 1004, 'yy', 1, '53');
INSERT INTO `t_student` (`id`, `student_id`, `name`, `course_id`, `course_score`) VALUES (12, 1004, 'yy', 2, '88');
INSERT INTO `t_student` (`id`, `student_id`, `name`, `course_id`, `course_score`) VALUES (13, 1005, 'uu', 1, '58');
INSERT INTO `t_student` (`id`, `student_id`, `name`, `course_id`, `course_score`) VALUES (14, 1005, 'uu', 2, '58');
INSERT INTO `t_student` (`id`, `student_id`, `name`, `course_id`, `course_score`) VALUES (100, 1001, 'jj', 1, '88');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
