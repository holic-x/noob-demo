package com.noob.algorithm.daily.thread;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 2.三个线程顺序打出1-100
 */
public class Solution002_01 {

    // lock + wait/notifyAll
    static AtomicInteger counter = new AtomicInteger(1);// 定义全局计数器
    static Object lock = new Object(); // 定义对象锁
    static int mark = 1; // 定义全局打印标识（1-线程A、2-线程B、3-线程C）

    public static void main(String[] args) {
        // 定义线程A
        Thread ta = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                // 当counter不足100则持续监听打印
                while (counter.get() < 100) {
                    synchronized (lock) {
                        // 判断是否为线程A的打印轮次
                        while (mark != 1) {
                            lock.wait();
                        }
                        // 打印数字
                        System.out.println("线程A:" + counter.getAndIncrement());
                        // 唤醒其他线程，切换打印标识
                        lock.notifyAll();
                        mark = 2;
                    }
                }
            }
        });

        // 定义线程B
        Thread tb = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                // 当counter不足100则持续监听打印
                while (counter.get() < 100) {
                    synchronized (lock) {
                        // 判断是否为线程B的打印轮次
                        while (mark != 2) {
                            lock.wait();
                        }
                        // 打印数字
                        System.out.println("线程B:" + counter.getAndIncrement());
                        // 唤醒其他线程，切换打印标识
                        lock.notifyAll();
                        mark = 3;
                    }
                }
            }
        });

        // 定义线程C
        Thread tc = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                // 当counter不足100则持续监听打印
                while (counter.get() < 100) {
                    synchronized (lock) {
                        // 判断是否为线程C的打印轮次
                        while (mark != 3) {
                            lock.wait();
                        }
                        // 打印数字
                        System.out.println("线程C:" + counter.getAndIncrement());
                        // 唤醒其他线程，切换打印标识
                        lock.notifyAll();
                        mark = 1;
                    }
                }
            }
        });

        // 启动线程测试
        ta.start();
        tb.start();
        tc.start();
    }

}
