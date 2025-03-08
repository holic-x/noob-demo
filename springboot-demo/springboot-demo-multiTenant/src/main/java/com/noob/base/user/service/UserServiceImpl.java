package com.noob.base.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.noob.base.user.mapper.UserMapper;
import com.noob.base.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public List<User> listUsers() {
        return this.list();
    }
}