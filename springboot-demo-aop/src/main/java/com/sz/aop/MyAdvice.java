package com.sz.aop;

import com.example.aop.WebLogAcpect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by lmb on 2018/9/5.
 */
@Aspect
@Component("sz-WebLogAcpect")
public class MyAdvice {

    private Logger logger = LoggerFactory.getLogger(MyAdvice.class);

    /**
     * 定义切入点，切入点为com.example.aop下的所有函数
     */
//    @Pointcut("execution(public * com.sz.aop..*.*(..))")
    @Pointcut("execution(public * com.sz.demo..*.*(..))")
    public void webLog(){}

    /**
     * 前置通知：在连接点之前执行的通知
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println("测试aop ing ...... doBefore");
    }

    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        System.out.println("测试aop ing ...... doAfterReturning");
    }


}