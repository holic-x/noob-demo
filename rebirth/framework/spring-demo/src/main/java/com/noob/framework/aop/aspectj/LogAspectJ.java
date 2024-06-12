package com.noob.framework.aop.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAspectJ {
    /**
     * 环绕通知
     */
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("-----------------------");
        System.out.println("环绕通知: 进入方法");
        Object o = pjp.proceed();
        System.out.println("环绕通知: 退出方法");
        return o;
    }

    /**
     * 前置通知
     */
    public void doBefore() {
        System.out.println("前置通知");
    }

    /**
     * 后置通知
     */
    public void doAfterReturning(String result) {
        System.out.println("后置通知, 返回值: " + result);
    }

    /**
     * 异常通知
     */
    public void doAfterThrowing(Exception e) {
        System.out.println("异常通知, 异常: " + e.getMessage());
    }

    /**
     * 最终通知
     */
    public void doAfter() {
        System.out.println("最终通知");
    }
}
