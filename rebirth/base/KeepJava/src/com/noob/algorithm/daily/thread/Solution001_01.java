package com.noob.algorithm.daily.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1.写1个双线程轮流打印1-100
 */
public class Solution001_01 {

    /**
     * 对象锁lock：wait/notifyAll + 等待标记mark
     */
    static AtomicInteger counter = new AtomicInteger(1); // 定义计数器
    static Object lock = new Object(); // 定义对象锁
    static int mark = 1; // 定义全局等待标记(1为线程A执行，2为线程B执行)

    public static void main(String[] args) {

        // 创建线程A
        Thread ta = new Thread(() -> {
            while (counter.get() < 100) {
                synchronized (lock) {
                    // 如果未轮到当前线程打印轮次则等待
                    while (mark != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("线程A执行" + counter.getAndIncrement());
                    lock.notifyAll(); // 唤醒
                    mark = 2; // 打印完成，切换等待标记
                }
            }
        });

        // 创建线程B
        Thread tb = new Thread(() -> {
            while (counter.get() < 100) {
                synchronized (lock) {
                    // 如果未轮到当前线程打印轮次则继续等待
                    while (mark != 2) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("线程B执行" + counter.getAndIncrement());
                    lock.notifyAll(); // 唤醒
                    mark = 1; // 打印完成，切换等待标记
                }
            }
        });

        // 启动线程A、B
        ta.start();
        tb.start();
    }

}
