package com.noob.algorithm.daily.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class Solution003_02 {
    static Object lock = new Object(); // 定义对象锁
    static AtomicInteger count = new AtomicInteger(1);// 定义计数器

    public static void printNum(int turn) {
        // 判断是否为当前轮次
        while (count.get() < 30) {
            synchronized (lock) {
                // 判断是否为当前轮次，如果不是则等待
                while (count.get() % 3 != turn) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                // 是当前轮次，则打印数字并执行自增
                System.out.println(Thread.currentThread().getName() + "线程执行：" + count.getAndIncrement());
                // 唤醒其他线程
                lock.notifyAll();
            }
        }
    }


    public static void main(String[] args) {
        // 定义线程A 打印轮次0
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                printNum(0);
            }
        }, "A");
        // 定义线程B 打印轮次1
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                printNum(1);
            }
        }, "B");
        // 定义线程C 打印轮次2
        Thread t3 = new Thread(new Runnable() {
            public void run() {
                printNum(2);
            }
        }, "C");

        // 线程启动
        t1.start();
        t2.start();
        t3.start();
    }
}