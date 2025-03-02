package com.noob.base.log.controller;

import com.noob.base.log.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("log-UserController")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String getUser(@RequestParam Long id) {
        return userService.getUserById(id);
    }
}