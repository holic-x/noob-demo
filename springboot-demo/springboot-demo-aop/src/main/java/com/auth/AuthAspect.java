package com.auth;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

@Aspect
@Component
public class AuthAspect {
    // 定义了一个切点:指定自定义注解的全路径
    @Pointcut("@annotation(com.auth.Auth)")
    public void authCut() {}

    @Before("authCut()")
    public void cutProcess(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("注解方式AOP开始拦截, 当前拦截的方法名: " + method.getName());
    }

    @After("authCut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("注解方式AOP执行的方法 :" + method.getName() + " 执行完了");
    }


    @Around("authCut()")
    public Object testCutAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("注解方式AOP拦截开始进入环绕通知.......");
        Object proceed = joinPoint.proceed();
        System.out.println("准备退出环绕......");
        return proceed;
    }

    /**
     * returning属性指定连接点方法返回的结果放置在result变量中
     * @param joinPoint 连接点
     * @param result    返回结果
     */
    @AfterReturning(value = "authCut()", returning = "result")
    public void afterReturn(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("注解方式AOP拦截的方法执行成功, 进入返回通知拦截, 方法名为: " + method.getName() + ", 返回结果为: " + result.toString());
    }

    @AfterThrowing(value = "authCut()", throwing = "e")
    public void afterThrow(JoinPoint joinPoint, Exception e) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("注解方式AOP进入方法异常拦截, 方法名为: " + method.getName() + ", 异常信息为: " + e.getMessage());
    }
}

