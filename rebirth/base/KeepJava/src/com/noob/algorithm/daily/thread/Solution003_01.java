package com.noob.algorithm.daily.thread;


import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程A、B、C 分别打印1 2 3 顺序执行10次
 */
public class Solution003_01 {


    static AtomicInteger counter = new AtomicInteger(1); // 计数器
    static Object lock = new Object(); // 对象锁
    static int mark = 1; // 定义全局打印标识

    public static void main(String[] args) {

        // 创建线程A
        Thread ta = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (counter.get() < 30) {
                    synchronized (lock) {
                        // 判断是否为当前线程的打印轮次
                        while (mark != 1) {
                            lock.wait();
                        }
                        System.out.println("线程A:" + 1);
                        counter.getAndIncrement();
                        // 唤醒其他线程，并更新mark
                        lock.notifyAll();
                        mark = 2;
                    }
                }
            }
        });

        // 创建线程B
        Thread tb = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (counter.get() < 30) {
                    synchronized (lock) {
                        // 判断是否为当前线程的打印轮次
                        while (mark != 2) {
                            lock.wait();
                        }
                        System.out.println("线程B:" + 2);
                        counter.getAndIncrement();
                        // 唤醒其他线程，并更新mark
                        lock.notifyAll();
                        mark = 3;
                    }
                }
            }
        });

        // 创建线程C
        Thread tc = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (counter.get() < 30) {
                    synchronized (lock) {
                        // 判断是否为当前线程的打印轮次
                        while (mark != 3) {
                            lock.wait();
                        }
                        System.out.println("线程C:" + 3);
                        counter.getAndIncrement();
                        // 唤醒其他线程，并更新mark
                        lock.notifyAll();
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
