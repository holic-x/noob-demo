package com.noob.base.demo.mapper;

import com.noob.base.demo.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Transactional
//    @Rollback(value = false) // 如果希望事务提交，则可指定回滚为false
    @Test
    void insert() {
        User user = new User("noob",20);
        userMapper.insert(user);
    }
}