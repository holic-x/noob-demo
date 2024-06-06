package com.noob.framework.di;

public class UserServiceImpl implements UserService{

    // 1.接口声明
    private UserDao userDao;

    // 2.提供setter方法（DI）
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(String userName) {
        // 调用UserDao的保存方法
        userDao.save(userName);
    }
}
