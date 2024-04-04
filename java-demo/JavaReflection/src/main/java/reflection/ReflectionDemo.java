package reflection;

import java.lang.reflect.Method;

public class ReflectionDemo {
    public static void main(String[] args) {
        try {
            // 使用Class.forName()方法加载类(加载指定的类)
            Class<?> clazz = Class.forName("reflection.User");

            // 使用clazz.newInstance()方法创建类的实例
            Object myClassInstance = clazz.newInstance();

            // 获取特定的方法
            Method myMethod = clazz.getMethod("callMyName", String.class);

            // 调用方法
            myMethod.invoke(myClassInstance, "noob");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
