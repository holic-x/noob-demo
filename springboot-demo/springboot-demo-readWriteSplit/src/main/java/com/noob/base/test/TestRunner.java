package com.noob.base.test;

import com.noob.base.user.entity.User;
import com.noob.base.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setName("John Doe");
        userService.writeUser(user);

        User readUser = userService.readUser(1L);
        System.out.println("Read user: " + readUser.getName());
    }
}