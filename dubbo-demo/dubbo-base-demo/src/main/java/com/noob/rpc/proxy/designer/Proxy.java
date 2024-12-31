package com.noob.rpc.proxy.designer;

/**
 * 自定义代理类
 */
public class Proxy implements Subject {

    // 定义被代理对象
    public RealSubject realSubject = new RealSubject();

    @Override
    public void operation() {
        System.out.println("proxy start ......");
        realSubject.operation();
        System.out.println("proxy end ......");
    }

}
