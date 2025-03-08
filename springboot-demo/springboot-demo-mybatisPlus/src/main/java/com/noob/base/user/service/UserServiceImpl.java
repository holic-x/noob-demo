package com.noob.base.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.noob.base.user.mapper.UserMapper;
import com.noob.base.user.model.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    // MyBatis-Plus 已经提供了基础的 CRUD 方法，无需手动编写
}