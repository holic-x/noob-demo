package com.noob.base.reflect.proxy;


// 公共接口（可以是抽象类、接口）
abstract class Subject{
    // 定义方法
    public abstract void request();
}

// 被代理对象（真实的实体）
class RealSubject extends Subject{

    @Override
    public void request() {
        System.out.println("真实请求");
    }
}

// 代理类定义
class Proxy extends Subject{
    // 定义被代理对象
    private RealSubject realSubject;

    @Override
    public void request() {
        if(null == realSubject){
            realSubject = new RealSubject();
        }
        // 执行真正的代理方法
        realSubject.request();
    }
}

/**
 * 静态代理
 */
public class StaticProxyDemo {

    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.request();
    }
}
