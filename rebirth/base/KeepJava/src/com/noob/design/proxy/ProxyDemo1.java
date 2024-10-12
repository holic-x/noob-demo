package com.noob.design.proxy;

/**
 * 代理模式：通过提供代理隐藏对目标对象的调用，一般用作方法的增强
 */
public class ProxyDemo1 {
    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.doProxy(new Target());
    }
}

// 代理目标（被代理对象）
class Target{
    public void doSth(){
        System.out.println("i am target, do something");
    }
}

// 定义代理类
class Proxy{
    public void doProxy(Target target){
        System.out.println("i am proxy");
        target.doSth();
        System.out.println("proxy end.....");
    }
}
