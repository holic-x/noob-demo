package com.aop;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void add() {
        System.out.println("执行add操作");
    }
}
