package com.noob.algorithm.daily.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * 1.写1个双线程轮流打印1-100
 */
public class Solution001_03 {

    /**
     * LockSupport
     */
    static AtomicInteger counter = new AtomicInteger(1); // 定义全局计数器
    static Thread[] threads = new Thread[2]; // 定义线程组（0-线程A、1-线程B）

    public static void main(String[] args) {

        threads[0] = new Thread(() -> {
            while (counter.get() < 100) {
                System.out.println("线程A:" + counter.getAndIncrement());
                LockSupport.unpark(threads[1]); // 唤醒线程B
                LockSupport.park(); // 阻塞当前线程
            }
        });

        threads[1] = new Thread(() -> {
            while (counter.get() < 100) {
                System.out.println("线程B:" + counter.getAndIncrement());
                LockSupport.unpark(threads[0]); // 唤醒线程A
                LockSupport.park(); // 阻塞当前线程
            }
        });

        // 启动线程
        threads[0].start();
        threads[1].start();
    }
}
