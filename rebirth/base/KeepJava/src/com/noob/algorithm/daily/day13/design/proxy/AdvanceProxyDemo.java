package com.noob.algorithm.daily.day13.design.proxy;

/**
 * 进阶版代理模式：
 * Subject、RealSubject、ProxySubject
 */
public class AdvanceProxyDemo {

    // ① 定义Subject（抽象主题）：公共接口定义
    static interface Subject{
        public void doSth();
    }

    // ② 定义RealSubject（真实主题，被代理对象）
    static class RealSubject implements Subject{
        @Override
        public void doSth() {
            System.out.println("real do sth......");
        }
    }

    // ③ 定义ProxySubject（代理主题，代理对象）
    static class ProxySubject implements Subject{

        // 定义被代理对象
        RealSubject realSubject = new RealSubject();

        @Override
        public void doSth() {
            System.out.println("proxy start......");
            realSubject.doSth();
            System.out.println("proxy end.....");
        }
    }

    public static void main(String[] args) {
        ProxySubject proxy = new ProxySubject();
        proxy.doSth();
    }

}
