package com.noob.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 03.三个线程顺序打印1-100
 */
public class Demo31 {
    // 基于对象锁实现
    static AtomicInteger counter = new AtomicInteger(1); // 计数器
    static Object obj = new Object(); // 对象锁
    static int mark = 1; // 打印标记：线程A-1 线程B-2 线程C-3

    public static void main(String[] args) {
        Thread ta = new Thread(() -> {
            while (counter.get() < 100) {
                synchronized (obj) {
                    if (mark == 1) {
                        System.out.println("线程A" + counter.getAndIncrement());
                        // 切换标记（synchronized不需要手动加解锁，锁定区域在{}范围内）
                        mark = 2;
                    }

                }
            }
        });

        Thread tb = new Thread(() -> {
            while (counter.get() < 100) {
                synchronized (obj) {
                    if (mark == 2) {
                        System.out.println("线程B" + counter.getAndIncrement());
                        // 切换标记（synchronized不需要手动加解锁，锁定区域在{}范围内）
                        mark = 3;
                    }
                }
            }
        });

        Thread tc = new Thread(() -> {
            while (counter.get() < 100) {
                synchronized (obj) {
                    if (mark == 3) {
                        System.out.println("线程C" + counter.getAndIncrement());
                        // 切换标记（synchronized不需要手动加解锁，锁定区域在{}范围内）
                        mark = 1;
                    }
                }
            }
        });

        // 启动线程
        ta.start();
        tb.start();
        tc.start();
    }
}
