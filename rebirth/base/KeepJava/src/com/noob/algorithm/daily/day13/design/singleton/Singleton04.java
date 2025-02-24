package com.noob.algorithm.daily.day13.design.singleton;

/**
 * 单例模式：双检锁模式
 * 懒汉式线程安全版本优化（只有在需要的时候才进行构建）
 * 线程安全 ver
 */
public class Singleton04 {
    // ① 构建一个私有的、静态的、属于自身的对象(volatile 关键字避免指令重排序问题)
    private static volatile Singleton04 instance = null; // 初始化为null

    // ② 构造函数私有化
    private Singleton04() {
        System.out.println("构造函数被调用....");
    }

    // ③ 对外提供一个静态的获取实例的方法
    public static Singleton04 getInstance() {
        // 判断instance是否为null（为null则进行创建）
        if (instance == null) { // 如果实例还没创建完成，则进入下一层校验
            synchronized (Singleton04.class){
                if(instance ==null){ // 只有抢占到锁资源的线程才可执行这一步
                    instance = new Singleton04();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        // 多线程获取实例
        // 验证多线程场景下懒汉式的不安全性
        Runnable task = ()->{
            String threadName = Thread.currentThread().getName();
            Singleton04 instance = Singleton04.getInstance();
            System.out.println("线程 " + threadName + "\t => " + instance.hashCode());
        };
        // 模拟多线程环境下使用 Singleton 类获得对象
        for(int i=0;i<100;i++){
            new Thread(task,"" + i).start();
        }
    }

}
