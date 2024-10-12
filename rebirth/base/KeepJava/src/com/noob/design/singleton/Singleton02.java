package com.noob.design.singleton;

/**
 * 单例模式：饿汉式(在定义的时候就初始化实例，这个过程不会出现并发安全问题)
 */
public class Singleton02 {

    // 1.创建一个静态的私有的自身变量(饿汉式是指定义的时候就直接进行初始化操作)
    private static Singleton02 instance = new Singleton02();

    // 2.构造函数私有化
    private Singleton02() {}

    // 3.对外提供一个方法用于获取该实例
    public static Singleton02 getInstance() {
        return instance;
    }

}
