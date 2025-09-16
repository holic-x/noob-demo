package com.noob.base.runtimeFailures.oom.controller;


import com.noob.base.runtimeFailures.oom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("oom-UserController")
@RequestMapping("/oom/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public String createUser() {
        userService.createUser();
        return "success";
    }


}