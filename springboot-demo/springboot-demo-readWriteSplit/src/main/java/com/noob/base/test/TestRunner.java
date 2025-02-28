package com.noob.base.test;

import com.noob.base.user.entity.User;
import com.noob.base.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class TestRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("test...");
    }

    /*
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        // 写操作
        User user = new User();
        user.setName("John Doe");
        userService.saveUser(user);

        // 读操作
        List<User> userList = userService.getAllUsers();
        System.out.println("Read user: " + userList.size());
    }

     */

}