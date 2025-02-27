package com.noob.base.demo.service;

import com.noob.base.demo.dao.UserRepository;
import com.noob.base.demo.model.entity.NoobUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public NoobUser createUser(String name, String email) {
        NoobUser user = new NoobUser();
        user.setName(name);
        user.setEmail(email);
        NoobUser savedUser = userRepository.save(user);
        // 记录操作日志
        System.out.println("用户创建成功: " + savedUser);
        return savedUser;
    }

    public List<NoobUser> getAllUsers() {
        List<NoobUser> users = userRepository.findAll();
        // 记录操作日志
        System.out.println("查询所有用户: " + users);
        return users;
    }
}