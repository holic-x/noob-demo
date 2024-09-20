package com.noob.thread.question4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 计数安全累加问题：100个线程，每个线程累加100次
 * 思路：线程池 + AtomicInteger
 */
public class Solution1 {

    // 定义计数器(此处final修饰AtomicInteger对象表示对象不可变，其内部的值还是可变的)
    private static final AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        // 定义线程池(固定大小100个线程)
        ExecutorService executors = Executors.newFixedThreadPool(100);
        // 提交100个任务，每个任务执行100次累加
        for (int i = 0; i < 100; i++) {
            executors.execute(new Runnable() {
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        // 原子操作，线程安全
                        count.incrementAndGet();
                    }
                }
            });
        }
        // 关闭线程池，不再接收新的任务
        executors.shutdown();

        // 输出最终统计的结果
        System.out.println(count);
    }
}
