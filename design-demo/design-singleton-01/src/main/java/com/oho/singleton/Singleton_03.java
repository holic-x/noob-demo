package com.oho.singleton;
/**
 * 饿汉模式(线程安全)
 */
public class Singleton_03 {

    // 构建一个静态的全局变量，并进行初始化
    private static Singleton_03 instance = new Singleton_03();

    // 构造函数私有化
    private Singleton_03() {
    }

    // 对外提供接口访问实例
    public static Singleton_03 getInstance() {
        return instance;
    }

}
