package com.noob.base.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.noob.base.demo.model.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

    // 从 指定的 test1 数据源获取数据
    public List<User> findAllByTest1();

    // 从 指定的 test2 数据源获取数据
    public List<User> findAllByTest2();

}
