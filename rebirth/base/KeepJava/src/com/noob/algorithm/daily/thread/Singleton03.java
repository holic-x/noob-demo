package com.noob.algorithm.daily.thread;

/**
 * 12.java 实现单例模式
 * - 懒汉式（只有在使用到的时候才创建对象）
 * - 多线程思路改造（考虑线程安全问题）
 */
public class Singleton03 {


    // 1.构建一个静态的私有的自身的对象
    private static volatile Singleton03 singleton; // volatile 避免指令重排序问题

    // 2.构造函数私有化
    public Singleton03() {
    }

    // 3.对外提供一个获取该单例对象的入口
    /*
    public static Singleton03 getSingleton1(){
        synchronized (Singleton03.class){
            if(singleton==null){
                singleton = new Singleton03();
            }
        }
        return singleton;
    }
     */

    public static synchronized Singleton03 getSingleton2() {
        if (singleton == null) {
            singleton = new Singleton03();
        }
        return singleton;
    }

    // 双检锁模式
    public static Singleton03 getSingleton() {
        if (singleton == null) {
            synchronized (Singleton03.class) {
                if (singleton == null) {
                    singleton = new Singleton03();
                }
            }
        }
        return singleton;
    }
}
