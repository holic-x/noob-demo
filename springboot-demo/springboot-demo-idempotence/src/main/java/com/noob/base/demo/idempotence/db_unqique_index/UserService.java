package com.noob.base.demo.idempotence.db_unqique_index;

import com.noob.base.demo.model.entity.User;
import org.springframework.dao.DuplicateKeyException;

/**
 * ② 基于【数据库唯一标识/唯一索引】实现幂等性
 */
public class UserService {
    public void registerUser(User user) {
        try {
            insertUserIntoDB(user); // 插入用户数据
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("用户名已存在");
        }
    }

    // 模拟插入数据到数据库
    private void insertUserIntoDB(User user) {
        // ... 插入数据 ...
    }
}