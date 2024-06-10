package com.noob.framework.springmvc.service;

import com.noob.framework.springmvc.dao.UserDaoImpl;
import com.noob.framework.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDaoImpl userDao;

    @Override
    public List<User> findUserList() {
        return userDao.findUser();
    }
}
