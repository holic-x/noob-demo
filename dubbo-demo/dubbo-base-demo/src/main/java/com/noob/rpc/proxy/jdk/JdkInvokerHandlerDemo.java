package com.noob.rpc.proxy.jdk;


import com.noob.rpc.proxy.designer.RealSubject;
import com.noob.rpc.proxy.designer.Subject;

/**
 * JDK 动态代理 demo 测试
 */
public class JdkInvokerHandlerDemo {

    public static void main(String[] args) {
        Subject subject = new RealSubject();
        JdkInvokerHandler invokerHandler = new JdkInvokerHandler(subject);
        // 获取代理对象
        Subject proxy = (Subject) invokerHandler.getProxy();
        // 调用方法
        proxy.operation(); // 会调用DemoInvokerHandler.invoke()方法
    }

}
