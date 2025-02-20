
package com.noob.algorithm.daily.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

class ThreadDemo1_04 {
    static AtomicInteger count = new AtomicInteger(1); // 定义计数器
    static Semaphore s1 = new Semaphore(1);
    static Semaphore s2 = new Semaphore(0);
    public static void main(String[] args) {
        // 线程A打印奇数
        Thread ta = new Thread(() -> {
            while (count.get() < 100) {
                while (count.get() % 2 == 1) {
                    try {
                        s1.acquire();
                        System.out.println("[奇数]线程A执行" + count.getAndIncrement());
                        s2.release();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        // 线程B打印偶数
        Thread tb = new Thread(() -> {
            while (count.get() <= 100) {
                while (count.get() % 2 == 0) {
                    try {
                        s2.acquire();
                        System.out.println("[偶数]线程B执行" + count.getAndIncrement());
                        s1.release();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        ta.start();
        tb.start();
    }
}