package com.noob.base.qrcode.controller;

import com.noob.base.qrcode.model.User;
import com.noob.base.qrcode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/confirm")
    public String confirmLogin(@RequestParam String token, @RequestParam String username) {
        // 这里应该验证token的有效性
        User user = userService.findByUsername(username);
        if (user != null) {
            return "Login successful for user: " + user.getUsername();
        } else {
            return "Login failed";
        }
    }
}