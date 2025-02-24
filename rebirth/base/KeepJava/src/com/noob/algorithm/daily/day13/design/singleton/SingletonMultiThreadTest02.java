
package com.noob.algorithm.daily.day13.design.singleton;

import lombok.SneakyThrows;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单例模式多线程测试
 */
public class SingletonMultiThreadTest02 {

    @SneakyThrows
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final int threadCount = 100; // 线程数量
        final Set<Singleton02> instances = Collections.synchronizedSet(new HashSet<>()); // 用于存储单例实例
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount); // 创建线程池

        // 使用 CountDownLatch 确保所有线程同时开始
        CountDownLatch startLatch = new CountDownLatch(1);
        // 使用 CountDownLatch 确保所有线程完成任务
        CountDownLatch endLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    startLatch.await(); // 等待所有线程准备就绪
                    Singleton02 instance = Singleton02.getInstance();
                    instances.add(instance); // 将实例添加到集合中
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    endLatch.countDown(); // 线程完成任务
                }
            });
        }

        startLatch.countDown(); // 所有线程同时开始
        endLatch.await(); // 等待所有线程完成
        executorService.shutdown(); // 关闭线程池

        System.out.println("创建的实例数量: " + instances.size());
    }

}