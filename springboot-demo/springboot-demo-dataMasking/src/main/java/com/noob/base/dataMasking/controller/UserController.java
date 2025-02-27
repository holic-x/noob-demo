package com.noob.base.dataMasking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DataMaskingInterceptor dataMaskingInterceptor;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) throws IllegalAccessException {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return (User) dataMaskingInterceptor.maskData(user);
    }
}