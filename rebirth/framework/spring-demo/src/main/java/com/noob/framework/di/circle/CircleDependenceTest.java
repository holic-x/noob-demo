package com.noob.framework.di.circle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 循环依赖测试
 */
public class CircleDependenceTest {

    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-circleDependence.xml");
        AuthorService authorService = (AuthorService) context.getBean("authorService");
        BookService bookService = (BookService) context.getBean("bookService");
        System.out.println(authorService.bookService);
        System.out.println(bookService.authorService);
    }

}
