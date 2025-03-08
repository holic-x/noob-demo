package com.noob.base.user.controller;

import com.noob.base.user.model.entity.User;
import com.noob.base.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        User findUser = userService.findByUsername(username);
        return findUser;
    }
}