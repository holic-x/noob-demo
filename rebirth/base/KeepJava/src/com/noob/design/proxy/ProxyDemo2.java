package com.noob.design.proxy;

/**
 * 代理模式：进阶版
 * 抽象主题：定义真实主题（被代理对象）和代理主题（代理对象）的公共接口
 * 真实主题：真正的业务逻辑实现
 * 代理主题：在不改变原有代码基础上实现对功能的扩展
 */
public class ProxyDemo2 {
    public static void main(String[] args) {
        Subject proxy = new ProxySubject();
        proxy.sayHello();
    }
}


// 抽象主题
interface Subject{
    public void sayHello();
}

// 真实主题
class RealSubject implements Subject{
    @Override
    public void sayHello() {
        System.out.println("RealSubject.sayHello");
    }
}

// 代理主题
class ProxySubject implements Subject{

    // 定义被代理对象
    RealSubject realSubject = new RealSubject();

    @Override
    public void sayHello() {
        System.out.println("proxy.sayHello start");
        realSubject.sayHello();
        System.out.println("proxy.sayHello end");
    }
}