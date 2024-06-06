package com.noob.framework.di.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDIAnnoTest {
    public static void main(String[] args) {
        // 读取配置文件并创建IOC容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext-anno.xml");
        // 通过IOC容器创建实例对象
         PersonController personController = (PersonController) applicationContext.getBean("personController");
         // 方法调用
        personController.add();
    }
}
