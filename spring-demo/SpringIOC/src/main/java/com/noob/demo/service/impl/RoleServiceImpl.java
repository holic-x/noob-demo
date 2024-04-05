package com.noob.demo.service.impl;

import com.noob.demo.dao.UserDao;
import com.noob.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    UserDao userDao;

    public void getBean() {
        System.out.println("RoleServiceImpl");
        userDao.getUser();
    }
}
