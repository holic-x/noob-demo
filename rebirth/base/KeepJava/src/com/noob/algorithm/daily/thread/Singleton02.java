package com.noob.algorithm.daily.thread;

/**
 * 12.java 实现单例模式
 * - 饿汉式（在定义的时候就构建对象）
 */
public class Singleton02 {

    // 1.构建一个静态的私有的自身的对象
    private static Singleton02 singleton = new Singleton02();

    // 2.构造函数私有化
    public Singleton02() {
    }

    // 3.对外提供一个获取该单例对象的入口
    public static Singleton02 getSingleton() {
        return singleton;
    }
}
