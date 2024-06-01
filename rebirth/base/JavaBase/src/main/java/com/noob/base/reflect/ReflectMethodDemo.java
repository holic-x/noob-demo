package com.noob.base.reflect;


import java.lang.reflect.Method;

class ReflectMethodDemo1{
    // 定义目标方法
    public static void target(int i) {
        new Exception("#" + i).printStackTrace();
    }

    public static void main(String[] args) throws Exception {
        // 通过抛出异常的方式打印Method.invoke的调用轨迹
        Class<?> clazz = Class.forName("com.noob.base.reflect.ReflectMethodDemo1");
        Method method = clazz.getMethod("target", int.class);
        method.invoke(null, 0);
    }
}

class ReflectMethodDemo2{
    // 定义目标方法
    public static void target(int i) {
        new Exception("#" + i).printStackTrace();
    }

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.noob.base.reflect.ReflectMethodDemo2");
        Method method = clazz.getMethod("target", int.class);
        // 校验阈值15
        for (int i = 0; i < 20; i++) {
            method.invoke(null, i);
        }
    }
}



/**
 * 反射方法 demo
 */
public class ReflectMethodDemo {
}
