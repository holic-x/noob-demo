package com.noob.algorithm.daily.thread;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 5.线程交叉打印12A34B56C.....
 * - 线程A打印数字、线程B打印字母
 */
public class Solution005_01 {

    static AtomicInteger numCounter = new AtomicInteger(1); // 定义全局计数器
    static AtomicInteger letterCounter = new AtomicInteger(1); // 定义全局计数器
    static Object lock = new Object();
    static int mark = 1; // 1-打印数字；2-打印字母

    public static void main(String[] args) {
        Thread ta = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (lock) {
                    while (numCounter.get() <= 52) {
                        // 校验是否为当前线程的打印轮次
                        while (mark != 1) {
                            lock.wait();
                        }
                        System.out.println(Thread.currentThread().getName() + ":" + numCounter.getAndIncrement());
                        System.out.println(Thread.currentThread().getName() + ":" + numCounter.getAndIncrement());
                        // 唤醒其他线程
                        lock.notifyAll();
                        mark = 2; // 切换打印标识
                    }
                }
            }
        }, "线程A");

        Thread tb = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (lock) {
                    while (letterCounter.get() < 26) {
                        // 校验是否为当前线程的打印轮次
                        while (mark != 2) {
                            lock.wait();
                        }

                        System.out.println(Character.toChars('A' + letterCounter.getAndIncrement()));
                        // System.out.println(Thread.currentThread().getName() + ":" + Character.toChars('A' + letterCounter.getAndIncrement()));
                        // 唤醒其他线程
                        lock.notifyAll();
                        mark = 1; // 切换打印标识
                    }
                }
            }
        }, "线程B");

        ta.start();
        tb.start();
    }
}
