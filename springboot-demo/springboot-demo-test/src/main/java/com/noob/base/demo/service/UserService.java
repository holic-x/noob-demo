package com.noob.base.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.noob.base.demo.model.entity.User;

public interface UserService extends IService<User> {
    public void testService(String key);
}
