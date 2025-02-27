package com.noob.base.demo.controller;

import com.noob.base.demo.model.entity.NoobUser;
import com.noob.base.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// todo : https://blog.csdn.net/wondersfan/article/details/126631804
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public NoobUser createUser(@RequestParam String name, @RequestParam String email) {
        return userService.createUser(name, email);
    }

    @GetMapping("/getAllUsers")
    public List<NoobUser> getAllUsers() {
        return userService.getAllUsers();
    }
}