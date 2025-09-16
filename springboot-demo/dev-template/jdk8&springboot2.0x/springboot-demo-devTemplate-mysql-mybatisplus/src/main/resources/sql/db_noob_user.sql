-- ----------------------------
-- Table structure for `db_noob_user`
-- ----------------------------
DROP TABLE IF EXISTS `db_noob_user`;
CREATE TABLE `db_noob_user` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                `age` int DEFAULT NULL,
                                `city` varchar(255) DEFAULT NULL,
                                `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                `create_time` datetime DEFAULT NULL,
                                `update_time` datetime DEFAULT NULL,
                                `is_deleted` tinyint DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                KEY `idx_name` (`name`),
                                KEY `idx_age` (`age`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of db_noob_user
-- ----------------------------
INSERT INTO `db_noob_user` VALUES ('1', 'Emma', '20', '深圳', null, null, null, null);
INSERT INTO `db_noob_user` VALUES ('2', 'Liam', '36', '北京', null, null, null, null);
INSERT INTO `db_noob_user` VALUES ('3', 'Noah', '25', '广州', null, null, null, null);
INSERT INTO `db_noob_user` VALUES ('4', 'Jackson', '15', '上海', null, null, null, null);
INSERT INTO `db_noob_user` VALUES ('5', 'Sophia', '41', '北京', null, null, null, null);
INSERT INTO `db_noob_user` VALUES ('6', 'Aiden', '47', '北京', null, null, null, null);
INSERT INTO `db_noob_user` VALUES ('7', 'Isabella', '23', '广州', null, null, null, null);
INSERT INTO `db_noob_user` VALUES ('8', 'Lucas', '17', '深圳', null, null, null, null);
