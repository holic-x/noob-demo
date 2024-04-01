package com.sz.demo;

import com.aop.MyAspect;
import com.aop.UserDAO;
import com.aop.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootDemoAopApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    private UserService userService;

    @Test
    void testAOP(){
        userService.add();
    }
}
