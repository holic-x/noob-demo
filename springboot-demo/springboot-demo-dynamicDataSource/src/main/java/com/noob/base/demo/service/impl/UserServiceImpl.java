package com.noob.base.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.noob.base.demo.mapper.UserMapper;
import com.noob.base.demo.model.entity.User;
import com.noob.base.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @DS("test1") // 指定数据源为test1
    @Override
    public List<User> findAllByTest1() {
        return userMapper.selectList(null);
    }

    @DS("test2") // 指定数据源为test2
    @Override
    public List<User> findAllByTest2() {
        return userMapper.selectList(null);
    }
}
