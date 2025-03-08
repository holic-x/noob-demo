package com.noob.algorithm.plan_archive.plan02.hot100.day13.design.singleton;

/**
 * 单例模式：饿汉式
 */
public class Singleton01 {

    // ① 构建一个私有的、静态的、属于自身的对象
    private static Singleton01 instance = new Singleton01(); // 定义的时候就进行初始化

    // ② 构造函数私有化
    private Singleton01() {
    }

    // ③ 对外提供一个静态的获取实例的方法
    public static Singleton01 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        // 普通测试获取单例
        System.out.println("普通测试获取单例");
        Singleton01 s1 = new Singleton01();
        Singleton01 s2 = new Singleton01();
        System.out.println(s1.getInstance());
        System.out.println(s2.getInstance());
        System.out.println("------------------------------------");
    }

}
