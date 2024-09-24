package com.noob.design.register.demo;


import java.util.HashMap;
import java.util.Map;

/**
 * 注册器
 */
public class Register {

    // 定义集合全局管理注册对象
    private static Map<String ,RegisterObject> map = new HashMap<String ,RegisterObject>();

    // 初始化
    static{
        map.put("obj1",new RegisterObject("对象1","obj1"));
        map.put("obj2",new RegisterObject("对象2","obj2"));
    }

    // 注册对象
    public void register(String type,RegisterObject registerObject){
        map.put(type,registerObject);
    }

    // 根据类型获取对象
    public RegisterObject getRegister(String type){
        return map.get(type);
    }
}
