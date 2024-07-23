package com.noob.base.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.noob.base.demo.mapper.UserMapper;
import com.noob.base.demo.model.entity.User;
import com.noob.base.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
