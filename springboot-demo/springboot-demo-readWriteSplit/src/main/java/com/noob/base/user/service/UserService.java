package com.noob.base.user.service;

import com.noob.base.user.dao.UserRepository;
import com.noob.base.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public User readUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public User writeUser(User user) {
        return userRepository.save(user);
    }
}