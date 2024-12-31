package com.noob.rpc.proxy.cglib;

// 被代理对象（类）
public class Target {
    public String operation(String str) {
        System.out.println(str);
        return "do sth:" + str;
    }
}
