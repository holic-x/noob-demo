-- 创建 user 表（如果不存在）
CREATE TABLE IF NOT EXISTS noob_user (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    username VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL,
    id_card VARCHAR(20) NOT NULL
    );

-- 插入测试数据
INSERT INTO noob_user (username, phone_number, email, id_card) VALUES ('张三', '13800138000', 'zhangsan@example.com', '110101199001011234');
INSERT INTO noob_user (username, phone_number, email, id_card) VALUES ('李四', '13900139000', 'lisi@example.com', '110101199002021235');
INSERT INTO noob_user (username, phone_number, email, id_card) VALUES ('王五', '13700137000', 'wangwu@example.com', '110101199003031236');