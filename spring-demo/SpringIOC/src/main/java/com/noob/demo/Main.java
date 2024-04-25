package com.noob.demo;

import com.noob.demo.config.MainConfiguration;
import com.noob.demo.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ioc = new AnnotationConfigApplicationContext(MainConfiguration.class);
        UserController bean = ioc.getBean(UserController.class);
        bean.getUser();
    }
}
