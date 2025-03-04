-- 租户表
CREATE TABLE tenant
(
    id   BIGINT PRIMARY KEY,
    name VARCHAR(255)
);

-- 用户表
CREATE TABLE noob_user
(
    id        BIGINT PRIMARY KEY,
    tenant_id BIGINT,
    name      VARCHAR(255)
);