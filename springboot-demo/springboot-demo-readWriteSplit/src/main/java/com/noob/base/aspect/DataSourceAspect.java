package com.noob.base.aspect;

import com.noob.base.datasource.DataSourceContextHolder;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("execution(* com.noob.base.user.service..*.read*(..))")
    public void readPointcut() {}

    @Pointcut("execution(* com.noob.base.user.service..*.write*(..))")
    public void writePointcut() {}

    @Before("readPointcut()")
    public void setReadDataSource() {
        DataSourceContextHolder.setDataSourceType("slave");
    }

    @Before("writePointcut()")
    public void setWriteDataSource() {
        DataSourceContextHolder.setDataSourceType("master");
    }

    @After("readPointcut() || writePointcut()")
    public void clearDataSource() {
        DataSourceContextHolder.clearDataSourceType();
    }
}