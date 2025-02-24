package com.noob.algorithm.daily.day13.design.proxy;

public class SimpleProxyDemo {

    // 代理目标
    static class Target {
        public void doSth() {
            System.out.println("do sth...");
        }
    }

    // 代理方
    static class Proxy {
        public void doProxy() {
            // 定义代理目标
            Target target = new Target();
            // 代理内容：执行代理方法，并额外扩展自己的一些内容
            System.out.println("i am proxy");
            target.doSth();
            System.out.println("proxy end.....");
        }
    }

    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.doProxy(); // 代理的目的是对使用者隐藏对目标对象的调用
    }

}
