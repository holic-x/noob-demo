package com.noob.algorithm.daily.design._1singleton;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单例模式：懒汉式（只有需要的时候才构建对象）
 */
public class Singleton01 {
    private static Singleton01 instance;

    private Singleton01() {
    }

    public static Singleton01 getInstance() {
        if (instance == null) {

            // 模拟竞态条件
            /*
            // 测试目的：此处模拟处理耗时，观察多线程访问场景会生成多个不同的实例
            try {
                Thread.sleep(10); // 让线程切换
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
             */

            instance = new Singleton01();
        }
        return instance;
    }

    public static void main(String[] args) throws InterruptedException {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(Singleton01.getInstance());
//        }

//        for (int i = 0; i < 100; i++) {
//            new Thread(()->{
//                System.out.println(Singleton01.getInstance());
//            }).start();
//        }

        testSingletonThreadSafety();
    }


    public static void testSingletonThreadSafety() throws InterruptedException {
        final int threadCount = 100;
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch endLatch = new CountDownLatch(threadCount);
        final Singleton01[] instances = new Singleton01[threadCount];

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            executor.execute(() -> {
                try {
                    startLatch.await(); // 等待所有线程就绪
                    instances[index] = Singleton01.getInstance();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    endLatch.countDown();
                }
            });
        }

        startLatch.countDown(); // 同时触发所有线程
        endLatch.await();      // 等待所有线程执行完毕

        // 检查所有线程获取的实例是否相同
        Singleton01 firstInstance = instances[0];
        for (int i = 1; i < threadCount; i++) {
            System.out.println("Singleton instance is not the same across threads" + firstInstance + "   " + instances[i]);
        }

        executor.shutdown();
    }
}
