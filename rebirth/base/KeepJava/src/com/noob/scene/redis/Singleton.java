package com.noob.scene.redis;

/**
 * 双检锁模式实现单例模式
 * 双检锁用于解决懒加载的线程安全（并发）问题
 */
public class Singleton {

    // 定义一个私有的、静态的、自身对象（通过volatile防止指令重排序，确保可见性、有序性）
    private static volatile Singleton instance;

    // 构造函数私有化
    private Singleton() {}

    // 对外提供公共方法获取单例对象
    public static Singleton getInstance() {
        // 双检锁模式：只有单例对象不存在的时候才进行创建，并且限定这个创建过程中只有一个线程可以创建
        if(instance == null) { // 第1道检查：判断单例对象是否存在，如果不存在才进行创建，否则直接返回
            // 同步代码块：确保只有1个线程进行创建
            synchronized (Singleton.class) {
                if(instance == null) { // 第2道检查：因为可能存在多个线程通过了第一道检查，并在进入同步代码块之前就创建好了对象，因此此处要进行第二道检查
                    instance = new Singleton();
                }
            }
        }

        // 返回构建好的单例对象
        return instance;
    }
}
