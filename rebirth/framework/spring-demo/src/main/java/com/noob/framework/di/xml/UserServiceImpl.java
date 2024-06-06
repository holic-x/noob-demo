package com.noob.framework.di.xml;

public class UserServiceImpl implements UserService{

    // 1.接口声明
    private UserDao userDao;

    // 2.构造器注入（DI）
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(String userName) {
        // 调用UserDao的保存方法
        userDao.save(userName);
    }
}
