package com.noob.algorithm.daily.thread;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 4.计数累加安全：100个线程，每个线程累加100次
 */
public class Solution004_01 {

    static AtomicInteger counter = new AtomicInteger(0); // 定义全局累加器

    @SneakyThrows
    public static void main(String[] args) {
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        // 提交100个任务，每个任务执行100次累加
        for (int i = 0; i < 100; i++) {
            executorService.submit(
                    new Runnable() {
                        @Override
                        public void run() {
                            for (int j = 0; j < 100; j++) {
                                counter.getAndIncrement();
                            }
                        }
                    }
            );
        }
        // 执行完成，关闭任务
        executorService.shutdown();
        // 等待所有任务完成
        executorService.awaitTermination(1, TimeUnit.HOURS);
        // 输出值
        System.out.println("final count:" + counter.get());
    }
}
