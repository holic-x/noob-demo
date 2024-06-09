package com.noob.framework.design;

import com.noob.framework.di.staticFactory.Staff;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SingletonTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-singleton.xml");
        Staff staff1 = (Staff) context.getBean("staff");
        System.out.println("staff1" + staff1);
        Staff staff2 = (Staff) context.getBean("staff");
        System.out.println("staff2" + staff2);
    }
}
