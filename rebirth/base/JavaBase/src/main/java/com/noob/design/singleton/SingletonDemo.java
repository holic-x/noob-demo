package com.noob.design.singleton;

// 01-饿汉式(在创建实例的时候就初始化对象实例)
class Singleton01 {
    // 1.自行创建静态实例（该实例私有化）
    private static Singleton01 instance = new Singleton01();

    // 2.构造函数私有化
    private Singleton01(){}

    // 3.对外提供访问该实例的入口
    public static Singleton01 getInstance(){
        return instance;
    }
}

// 02-饱汉式(只有在要用到实例的时候才创建)
class Singleton02{
    // 1.自行创建实例（该实例私有化）
    private static Singleton02 instance = null;

    // 2.构造函数私有化
    private Singleton02(){}

    // 3.对外提供访问该实例的入口
    public static Singleton02 getInstance(){
        // 饱汉式是只有在用到实例的时候进行判断，如果实例为null则常见
        if(instance==null){
            instance = new Singleton02();
        }
        return instance;
    }
}

/**
 * 单例模式案例
 */
public class SingletonDemo {

    public static void main(String[] args) {
        // 1.饿汉式
        Singleton01 singleton011 = Singleton01.getInstance();
        Singleton01 singleton012 = Singleton01.getInstance();
        System.out.println(singleton011==singleton012);


        // 2.饱汉式（懒汉式）
        Singleton02 singleton021 = Singleton02.getInstance();
        Singleton02 singleton022 = Singleton02.getInstance();
        System.out.println(singleton021==singleton022);

    }

}
