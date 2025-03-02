package com.noob.base.user.service;

import com.noob.base.datasource.DataSourceContextHolder;
import com.noob.base.user.dao.UserRepository;
import com.noob.base.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        DataSourceContextHolder.setDataSource("master");
        User savedUser = userRepository.save(user);
        DataSourceContextHolder.clearDataSource();
        return savedUser;
    }

    public List<User> getAllUsers() {
        DataSourceContextHolder.setDataSource("slave");
        List<User> users = userRepository.findAll();
        DataSourceContextHolder.clearDataSource();
        return users;
    }
}