package com.noob.algorithm.daily.day13.design.register;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础版本注册器（最常见的属性注册）
 */
public class SimpleRegister {
    // 自定义注册对象
    static class RegisterObject {
        int id;
        String name;
        String desc;

        RegisterObject(int id, String name, String desc) {
            this.id = id;
            this.name = name;
            this.desc = desc;
        }
    }

    // 自定义注册器
    static class Register {
        // ① 维护一个全局的缓存：存储已注册对象
        static Map<Integer, RegisterObject> map = new HashMap<>();

        // ② 初始化
        static {
            map.put(1, new RegisterObject(1, "obj1", "obj111"));
            map.put(2, new RegisterObject(1, "obj2", "obj222"));
        }

        // ③ 提供一个注册方法
        public void register(RegisterObject obj) {
            map.put(obj.id, obj);
        }

        // ④ 提供一个获取注册对象的方法
        public RegisterObject getObj(int id) {
            return map.get(id);
        }
    }

    public static void main(String[] args) {
        // 构建注册器
        Register r = new Register();
        // 注册对象
        r.register(new RegisterObject(5, "obj5", "obj555"));
        // 获取对象
        RegisterObject getObj = r.getObj(5);
        System.out.println(getObj.id + "-" + getObj.name + "-" + getObj.desc);
    }

}
