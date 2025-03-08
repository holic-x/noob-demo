package com.noob.base.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.noob.base.user.model.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> listUsers();
}