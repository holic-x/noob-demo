package com.noob.algorithm.daily.thread;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 8.双线程 一个打印abcd，一个打印1234，需求交替打印出a1b2c3d4a1b2c3d4; 打印10轮
 */
public class Solution008_01 {

    // 定义全局计数器
    static AtomicInteger numCounter = new AtomicInteger(0);
    static AtomicInteger letterCounter = new AtomicInteger(0);

    static Object lock = new Object();
    static int mark = 1;

    public static void main(String[] args) {
        Thread ta = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (lock) {
                    // 字母会计数到40
                    while (letterCounter.get() < 40) {
                        // 校验是否为当前线程打印轮次
                        while (mark != 1) {
                            lock.wait();
                        }
                        System.out.print(Thread.currentThread().getName());
                        System.out.println(Character.toChars('a' + letterCounter.getAndIncrement() % 4));
                        // 唤醒其他线程，切换打印标识
                        lock.notifyAll();
                        mark = 2;
                    }
                }
            }
        }, "A");

        Thread tb = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (lock) {
                    // 数字会计数到40
                    while (numCounter.get() < 40) {
                        // 校验是否为当前线程打印轮次
                        while (mark != 2) {
                            lock.wait();
                        }
                        System.out.print(Thread.currentThread().getName());
                        System.out.println(numCounter.getAndIncrement() + 1 % 4);
                        // 唤醒其他线程，切换打印标识
                        lock.notifyAll();
                        mark = 1;
                    }
                }
            }
        }, "B");

        // 启动线程
        ta.start();
        tb.start();
    }
}
