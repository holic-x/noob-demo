package com.noob.rpc.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLib 动态代理
 */
public class CGLibProxy implements MethodInterceptor {

    // ① 初始化Enhancer对象
    private Enhancer enhancer = new Enhancer();

    // ② 获取代理
    public Object getProxy(Class clazz) {
        enhancer.setSuperclass(clazz); // 指定生成的代理类的父类
        enhancer.setCallback(this); // 设置Callback对象
        return enhancer.create(); // 通过ASM字节码技术动态创建子类实例
    }

    // ③ 实现MethodInterceptor接口的intercept方法
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("前置处理......");
        Object result = methodProxy.invokeSuper(o, objects); // obj args 调用父类中的方法
        System.out.println("后置处理......");
        return result;
    }
}
