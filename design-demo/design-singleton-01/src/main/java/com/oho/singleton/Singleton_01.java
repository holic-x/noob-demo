package com.oho.singleton;

/**
 * 懒汉模式(线程不安全)
 */
public class Singleton_01 {

    // 构建一个静态的全局的变量供内部调用
    private static Singleton_01 instance;

    // 构造函数私有化
    private Singleton_01() {
    }

    // 对外提供公共接口
    public static Singleton_01 getInstance(){
        // 懒汉模式:在调用的时候进行判断，如果实例为null则构建
        if (null != instance) {
            return instance;
        }
        return new Singleton_01();
    }

}
