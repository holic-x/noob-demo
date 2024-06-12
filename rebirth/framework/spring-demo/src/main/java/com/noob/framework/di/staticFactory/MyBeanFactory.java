package com.noob.framework.di.staticFactory;

public class MyBeanFactory {
    // 创建静态工厂方法实例化Bean对象
     public static Staff createBean(){
         return new Staff();
     }
}
