package com.noob.base.dataMasking.controller;

import com.noob.base.dataMasking.dao.UserRepository;
import com.noob.base.dataMasking.entity.User;
import com.noob.base.dataMasking.interceptor.DataMaskingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        // 获取脱敏后的信息
        User maskingUser = (User) dataMaskingInterceptor.maskData(user);
        return maskingUser;
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList;
    }
}