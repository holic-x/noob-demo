package com.noob.algorithm.daily.thread;


import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程A、B、C 分别打印1 2 3 顺序执行10次
 */
public class Solution003_03 {

    static AtomicInteger counter = new AtomicInteger(1); // 计数器
    static Object lock = new Object(); // 对象锁
    // static int mark = 1; // 定义全局打印标识

    // 根据mark执行打印操作
    @SneakyThrows
    static void print(int mark) {
        while (counter.get() < 100) {
            synchronized (lock) {
                // 非当前线程打印轮次，等待
                while (counter.get() % 3 != mark) {
                    lock.wait();
                }
                // 执行打印操作
                System.out.println(Thread.currentThread().getName() + ":" + (mark + 1));
                counter.getAndIncrement(); // 计数+1
                // 唤醒其他线程(此处的mark由counter决定)
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) {

        Thread ta = new Thread(new Runnable() {
            @Override
            public void run() {
                print(0);
            }
        });
        ta.setName("线程A");

        Thread tb = new Thread(new Runnable() {
            @Override
            public void run() {
                print(1);
            }
        });
        tb.setName("线程B");

        Thread tc = new Thread(new Runnable() {
            @Override
            public void run() {
                print(2);
            }
        });
        tc.setName("线程C");

        // 启动线程
        ta.start();
        tb.start();
        tc.start();
    }

}

