package com.noob.framework.aop.aspectj.service;

public class UserServiceImpl {

    public void method(){
        System.out.println("hello spring aop");
    }

    public void methodExc() throws Exception{
        System.out.println("sth wrong throw Exception");
        throw new Exception();
    }

}
