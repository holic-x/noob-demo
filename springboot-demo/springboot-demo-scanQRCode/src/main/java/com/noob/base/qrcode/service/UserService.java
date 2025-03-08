package com.noob.base.qrcode.service;

import com.noob.base.qrcode.dao.UserRepository;
import com.noob.base.qrcode.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}