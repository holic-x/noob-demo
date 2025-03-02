# 创建用户表
CREATE TABLE user
(
    id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    age   INT          NOT NULL,
    email VARCHAR(255) NOT NULL
);