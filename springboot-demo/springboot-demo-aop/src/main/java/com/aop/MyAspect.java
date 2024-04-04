package com.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 定义切面
 */
@Component // 注入bean
@Aspect // 定义切面
public class MyAspect {

    /** 切入点表达式(如果多处需要引用，则可定义一个切点表达式供切面引入) */
    @Pointcut("execution(* com.aop.UserService.add(..))")
    public void logPointCut(){}

    // 方式1：通过切点切入
    @Before("logPointCut()")
    public void writeLog() {
        System.out.println("前置增强......记录日志");
    }

    // 方式2：直接织入
    @AfterReturning("execution(* com.aop.UserService.add(..))")
    public void doSth() {
        System.out.println("后置增强......执行操作后处理某些事物......");
    }
}
