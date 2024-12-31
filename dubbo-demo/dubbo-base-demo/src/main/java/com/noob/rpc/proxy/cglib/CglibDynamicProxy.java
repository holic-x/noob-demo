package com.noob.rpc.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 基于Cglib的动态代理（实现MethodInterceptor）
 */
public class CglibDynamicProxy implements MethodInterceptor {

    // 被代理对象
    private Object target;

    // 构造函数初始化
    public CglibDynamicProxy(Object target) {
        this.target = target;
    }

    // 创建并返回代理实例
    public Object getProxyInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    // 实现intercept方法
    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("Before method invocation: " + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("After method invocation: " + method.getName());
        return result;
    }
}