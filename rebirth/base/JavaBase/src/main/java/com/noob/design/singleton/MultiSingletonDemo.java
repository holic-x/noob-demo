package com.noob.design.singleton;

// 饱汉式|懒汉式(只有在要用到实例的时候才创建)
class LazySingleton {
    // 1.自行创建实例（该实例私有化）
    private volatile static LazySingleton instance = null;

    // 定义全局计数器，统计构造函数调用次数
    private static int count;

    // 2.构造函数私有化
    private LazySingleton() {
        System.out.println("LazySingleton 构造函数被调用第" + (count++) + "次");
    }

    // 3.对外提供访问该实例的入口
    public static LazySingleton getInstance() {
        // 饱汉式是只有在用到实例的时候进行判断，如果实例为null则常见
        if(instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}

/**
 * 多线程环境 验证单例模式
 */
public class MultiSingletonDemo {
    public static void main(String[] args) {
        // 验证多线程场景下懒汉式的不安全性
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println("线程 " + threadName + "\t => " + instance.hashCode());
        };
        // 模拟多线程环境下使用 Singleton 类获得对象
        for (int i = 0; i < 100; i++) {
            new Thread(task, "" + i).start();
        }
    }
}
