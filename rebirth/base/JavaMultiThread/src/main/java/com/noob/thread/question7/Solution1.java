package com.noob.thread.question7;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 两个线程交替打印出a1b2c3.....z26
 */
public class Solution1 {
    // 定义对象锁
    static Object lock = new Object();
    // 定义打印标识
    static int printMark = 1;
    // 定义数字计数器
    static AtomicInteger numCount = new AtomicInteger(1);
    // 定义字母计数器
    static AtomicInteger letterCount = new AtomicInteger(0);

    public static void main(String[] args) {
        // 定义线程1打印数字
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while (numCount.get() <= 26) {
                    synchronized (lock) {
                        // 判断是否为当前轮次（打印数字）
                        while (printMark != 1) {
                            // 非打印数字轮次，持续等待
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        // 打印数字、计数+1、切换标识、唤醒线程
                        System.out.println(numCount.getAndIncrement());
                        printMark = 2;
                        lock.notifyAll();
                    }
                }
            }
        });

        // 定义线程2打印字母
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                while (letterCount.get() < 26) {
                    synchronized (lock) {
                        // 判断是否为当前轮次（打印字母）
                        while (printMark != 2) {
                            // 非打印字母轮次，持续等待
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        // 打印字母、计数+1、切换标识、唤醒线程
                        System.out.println(Character.toChars('A' + letterCount.getAndIncrement()));
                        printMark = 1;
                        lock.notifyAll();
                    }
                }
            }
        });
        // 启动线程
        t1.start();
        t2.start();
    }
}
