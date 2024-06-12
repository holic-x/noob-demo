package com.noob.framework.di.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDITest {
    public static void main(String[] args) {
        // 读取xml配置
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 通过IOC容器获得对象
        UserService userService = (UserService) applicationContext.getBean("userService"); // applicationContext.getBean("userService",UserService.class);
        // 调用方法测试
        userService.addUser("noob");
    }
}
