
package com.noob.algorithm.plan_archive.plan02.hot100.day13.design.singleton;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * 单例模式多线程测试
 */
public class SingletonMultiThreadTest01 {
    public static void main(String[] args) throws InterruptedException {
        final int threadCount = 100; // 线程数量
        final Set<Singleton02> instances = new HashSet<>(); // 用于存储单例实例
        final CountDownLatch latch = new CountDownLatch(threadCount); // 用于同步线程

        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                Singleton02 instance = Singleton02.getInstance();
                synchronized (instances) {
                    instances.add(instance); // 将实例添加到集合中
                }
                latch.countDown(); // 线程完成任务
            }).start();
        }

        latch.await(); // 等待所有线程完成
        System.out.println("创建的实例数量: " + instances.size());
    }
}