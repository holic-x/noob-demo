package com.noob.framework.di.staticFactory;

import com.noob.framework.di.autowire.Student;
import com.noob.framework.di.autowire.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDIStaticFactoryTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-staticFactory.xml");
        // 通过执行静态工厂方法获取到Bean对象
        Staff staff = (Staff) applicationContext.getBean("myBeanFactory");
        staff.say();
    }
}
