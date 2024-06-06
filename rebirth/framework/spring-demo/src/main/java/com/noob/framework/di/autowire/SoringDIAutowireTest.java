package com.noob.framework.di.autowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SoringDIAutowireTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-autowire.xml");
        StudentService studentService = applicationContext.getBean("studentService", StudentService.class);
        studentService.addUser(new Student("noob","0001"));
    }
}
