package com.noob.framework.di.instanceFactory;

import com.noob.framework.di.staticFactory.Staff;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDIInstanceFactoryTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-instanceFactory.xml");
        // 通过执行静态工厂方法获取到Bean对象
        Boss boss = (Boss) applicationContext.getBean("boss");
        boss.say();
    }
}
