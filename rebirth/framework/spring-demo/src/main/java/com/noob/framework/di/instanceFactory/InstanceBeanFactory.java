package com.noob.framework.di.instanceFactory;

import com.noob.framework.di.staticFactory.Staff;

public class InstanceBeanFactory {

    // 构造函数初始化
    public InstanceBeanFactory(){
        System.out.println("instance factory init");
    }

    // 创建普通方法实例化Bean对象
     public Boss createBean(){
         return new Boss();
     }
}
