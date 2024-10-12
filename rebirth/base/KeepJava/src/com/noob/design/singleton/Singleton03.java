package com.noob.design.singleton;

/**
 * 双检锁模式（基于懒汉式的并发安全改造）
 * 其核心在于降低锁的粒度，以此提升并发能力支持
 */
public class Singleton03 {

    // 1.定义一个私有的静态的自身变量
    private static volatile Singleton03 instance;

    // 2.构造函数私有化
    private Singleton03() {}

    // 3.对外提供方法获取该实例
    public static Singleton03 getInstance() {
        // 进行初始化操作，此处需注意并发安全问题处理
        if (instance == null) {
            synchronized (Singleton03.class){ // 类锁
                if(instance == null) {
                    instance = new Singleton03();
                }
            }
        }
        return instance;
    }
}
