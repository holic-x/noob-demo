-- 初始化租户数据
INSERT INTO tenant (id, name) VALUES (1, 'Tenant A');
INSERT INTO tenant (id, name) VALUES (2, 'Tenant B');

-- 初始化用户数据
INSERT INTO noob_user (id, tenant_id, name) VALUES (1, 1, 'User 1 from Tenant A');
INSERT INTO noob_user (id, tenant_id, name) VALUES (2, 1, 'User 2 from Tenant A');
INSERT INTO noob_user (id, tenant_id, name) VALUES (3, 2, 'User 3 from Tenant B');