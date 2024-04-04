package com.aop;

public class UserDAOExtend extends UserDAO{
    public void moreAdd(){
        System.out.println("before....");
        // 调用父类方法
        add();
        System.out.println("after....");
    }

    public static void main(String[] args) {
        UserDAOExtend userDAOExtend = new UserDAOExtend();
        userDAOExtend.moreAdd();
    }
}
