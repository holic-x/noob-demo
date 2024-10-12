package com.noob.design.singleton;

/**
 * 单例模式：懒汉式（线程安全、线程不安全）
 */
public class Singleton01 {
    // 1.定义一个私有的自身的静态对象
    private static Singleton01 singleton ;

    // 2.构造函数私有化
    private Singleton01(){
    }

    // 3.对外提供一个公共的方法用于操作该对象
    // 存在线程安全问题
    public static Singleton01 getInstance(){
        // 懒汉式的核心在于只有在真正使用的时候才会去初始化对象
        if(singleton == null){
            singleton = new Singleton01();
        }
        return singleton;
    }

    /**
     *  线程安全处理，通过synchronized加锁实现
     */
    public static synchronized Singleton01 getInstanceSafe(){
        // 懒汉式的核心在于只有在真正使用的时候才会去初始化对象
        if(singleton == null){
            singleton = new Singleton01();
        }
        return singleton;
    }

    public static void main(String[] args) {
        // 测试
        Singleton01 singleton01 = new Singleton01();
        System.out.println(singleton01.getInstance());

        Singleton01 singleton02 = new Singleton01();
        System.out.println(singleton02.getInstance());

        // 当这两个对象实例输出的内容都是指向同一个内存，说明其是同一个对象
    }
}
