package com.noob.rpc.proxy.jdk;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 动态代理（实现InvocationHandler）
 */
public class JdkInvokerHandler implements InvocationHandler {

    // 被代理对象
    private Object target;

    // 初始化
    JdkInvokerHandler(Object target) {
        this.target = target;
    }

    // 创建代理实例并返回
    public Object getProxy() {
        // 创建代理对象
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy start ....."); // 前置处理
        Object res = method.invoke(target, args); // 执行业务方法逻辑
        System.out.println("proxy end ......"); // 后置处理
        return res;
    }
}
