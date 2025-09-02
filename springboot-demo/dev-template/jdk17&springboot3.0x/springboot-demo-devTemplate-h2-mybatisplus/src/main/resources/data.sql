-- 创建 user 表（如果不存在）
CREATE TABLE IF NOT EXISTS db_noob_user (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(50) NOT NULL,
        email VARCHAR(100),
        age INT,
        create_time TIMESTAMP,
        update_time TIMESTAMP,
        is_deleted INT DEFAULT 0
    );

-- 插入测试数据
INSERT INTO db_noob_user (username, email, age, create_time, update_time) VALUES
('张三', 'zhangsan@example.com', 25, NOW(), NOW()),
('李四', 'lisi@example.com', 30, NOW(), NOW()),
('王五', 'wangwu@example.com', 28, NOW(), NOW());