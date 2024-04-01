package com.sz.demo;

import com.aop.MyAspect;
import com.aop.UserDAO;
import com.aop.UserService;
import com.auth.Auth;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
