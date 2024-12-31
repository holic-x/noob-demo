package com.noob.rpc.proxy.cglib;

import com.noob.rpc.proxy.designer.RealSubject;
import com.noob.rpc.proxy.designer.Subject;

/**
 * 基于Cglib动态代理测试
 */
public class CglibDynamicProxyDemo {

    // 接口代理测试
    public static void testInterface() {
        // ① 接口代理
        RealSubject realSubject = new RealSubject();
        CglibDynamicProxy proxy = new CglibDynamicProxy(realSubject);
        Subject subject = (Subject) proxy.getProxyInstance();
        subject.operation();
    }

    // 类代理测试
    public static void testClass() {
        // ② 类代理
        CglibDynamicProxy proxy = new CglibDynamicProxy(new Target());
        Target target = (Target) proxy.getProxyInstance();
        target.operation("test ...");
    }

    public static void main(String[] args) {
        testInterface();
        System.out.println("---------------------------------------");
        testClass();
    }
}