package com.noob.algorithm.daily.day13.design.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单例模式：懒汉式（只有在需要的时候才进行构建）
 * 线程不安全 ver
 */
public class Singleton02 {
    // ① 构建一个私有的、静态的、属于自身的对象
    private static Singleton02 instance = null; // 初始化为null

    // ② 构造函数私有化
    private Singleton02() {
        System.out.println("构造函数被调用....");
    }

    // ③ 对外提供一个静态的获取实例的方法
    public static Singleton02 getInstance() {
        // 判断instance是否为null（为null则进行创建）
        if (instance == null) {
            instance = new Singleton02();
        }
        return instance;
    }

    public static void main(String[] args) {
        // 多线程获取实例
        /*
        // 验证多线程场景下懒汉式的不安全性
        Runnable task = ()->{
            String threadName = Thread.currentThread().getName();
            Singleton02 instance = Singleton02.getInstance();
            System.out.println("线程 " + threadName + "\t => " + instance.hashCode());
        };
        // 模拟多线程环境下使用 Singleton 类获得对象
        for(int i=0;i<100;i++){
            new Thread(task,"" + i).start();
        }
         */

        /*
        System.out.println("多线程获取实例");
        for (int i = 1; i <= 20; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    Singleton02 s = new Singleton02();
                    System.out.println(Thread.currentThread().getName() + "获取的实例：" + s.getInstance().hashCode());
                }
            }, "线程" + i);
            t.start();
        }
         */

        ExecutorService executor = Executors.newFixedThreadPool(100);
        // 创建10个任务
        for (int i = 1; i <= 100; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    Singleton02 s = new Singleton02();
                    System.out.println(Thread.currentThread().getName() + "获取实例：" + s.getInstance().hashCode());
                }
            });
        }
    }

}
