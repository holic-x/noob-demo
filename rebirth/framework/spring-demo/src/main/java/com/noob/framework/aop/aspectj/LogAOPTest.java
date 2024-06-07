package com.noob.framework.aop.aspectj;

import com.noob.framework.aop.aspectj.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// 日志AOP测试
public class LogAOPTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-aop.xml");
        UserServiceImpl userService = context.getBean("demoService", UserServiceImpl.class);
        // 执行普通方法方法
        userService.method();

        // 异常方法测试
        try {
            userService.methodExc();
        } catch (Exception e) {
            System.out.println("异常捕获处理");
        }
    }
}
