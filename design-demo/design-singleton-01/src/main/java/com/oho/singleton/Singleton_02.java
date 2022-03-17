package com.oho.singleton;

/**
 * 懒汉模式(线程安全)
 */
public class Singleton_02 {

    // 构建一个静态的全局的变量供内部调用
    private static Singleton_02 instance;

    // 构造函数私有化
    private Singleton_02() {
    }

    // 对外提供公共接口：将锁加到方法上，满足线程安全，所有访问需要锁占用
    public static synchronized Singleton_02 getInstance(){
        // 懒汉模式:在调用的时候进行判断，如果实例为null则构建
        if (null != instance) {
            return instance;
        }
        return new Singleton_02();
    }

}
