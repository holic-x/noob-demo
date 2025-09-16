package com.noob.algorithm.daily.design._1singleton;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSingleton {

    public static void main(String[] args) throws InterruptedException {
        TestSingleton.testSingletonThreadSafety();
    }

    public static void testSingletonThreadSafety() throws InterruptedException {
        final int threadCount = 100;
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch endLatch = new CountDownLatch(threadCount);
        final Singleton04[] instances = new Singleton04[threadCount];

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            executor.execute(() -> {
                try {
                    startLatch.await(); // 等待所有线程就绪
                    instances[index] = Singleton04.getInstance();
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
        Singleton04 firstInstance = instances[0];
        for (int i = 1; i < threadCount; i++) {
            System.out.println("Singleton instance is not the same across threads" + firstInstance + "   " + instances[i]);
        }

        executor.shutdown();
    }
}

