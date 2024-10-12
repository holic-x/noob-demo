package com.noob.design.register;

import java.util.HashMap;

/**
 * 注册器模式
 * 用于全局注册一些对象，便于被其他对象发现和使用
 */
public class RegisterDemo {

    public static void main(String[] args) {
        Register.register("005","didididi");
        System.out.println(Register.getObj("002"));
    }

}

// 注册对象
class RegisterObject{
    // 对象属性定义
    private String name;
    private String descr;

    // 构造函数dignity
    public RegisterObject(String name, String descr){
        this.name = name;
        this.descr = descr;
    }
}

// 注册器
class Register{

    // 定义一个集合存储元素（存储注册对象）
    private static HashMap<String,RegisterObject> map;

    // 初始化集合(存储一些默认的全局对象)
    static{
        map = new HashMap<>();
        map.put("001", new RegisterObject("obj001","obj001 hahaha"));
        map.put("002", new RegisterObject("obj002","obj001 hahaha"));
    }

    // 对外提供注册方法（开放封闭原则：对修改封闭、对扩展开放）
    public static void register(String name,String descr){
        map.put(name,new RegisterObject(name,descr)); // 此处进一步优化还需对key做校验
    }

    // 对外提供访问对象的方法
    public static RegisterObject getObj(String key){
        return map.get(key);
    }
}