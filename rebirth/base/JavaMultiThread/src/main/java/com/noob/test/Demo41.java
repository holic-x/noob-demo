package com.noob.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 04.计数安全累加（100个线程每个线程累加100次）
 * 思路：通过线程池和原子操作类实现
 */
public class Demo41 {

    // 定义计数器
    static AtomicInteger counter = new AtomicInteger(1);

    public static void main(String[] args) {
        // 定义一个100个线程的固定线程池
        ExecutorService execotors = Executors.newFixedThreadPool(100);
        // 执行操作
        for (int k = 0; k < 100; k++) {
            execotors.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        System.out.println(counter.getAndIncrement());
                    }
                }
            });
        }
        // 执行完成，关闭资源
        execotors.shutdown();
        // 输出最终结果
        System.out.println("累加结果：" + counter.get());
    }
}
