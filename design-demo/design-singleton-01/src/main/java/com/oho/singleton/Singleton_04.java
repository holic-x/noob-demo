package com.oho.singleton;

/**
 * 使⽤类的内部类(线程安全)
 */
public class Singleton_04 {

    // 构建静态内部类实现单例模式
    private static class SingletonHolder {
        private static Singleton_04 instance = new Singleton_04();
    }

    // 构造函数私有化
    private Singleton_04() {
    }

    // 对外提供接口访问实例
    public static Singleton_04 getInstance() {
        return SingletonHolder.instance;
    }

}
