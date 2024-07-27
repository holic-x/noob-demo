package com.noob.base.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserServiceTest {

    // UserService对象，模拟测试
    @Autowired
    private UserService userService;

    @Test
    void testService() {
        userService.testService("啊哈哈");
    }
}