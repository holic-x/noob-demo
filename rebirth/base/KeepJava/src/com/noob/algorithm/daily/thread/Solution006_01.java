package com.noob.algorithm.daily.thread;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 6.交替打印a1b2...
 */
public class Solution006_01 {
    // 定义全局计数器
    static AtomicInteger numCounter = new AtomicInteger(1);
    static AtomicInteger letterCounter = new AtomicInteger(0);
    static int mark = 1; // 全局打印标识
    static Object lock = new Object();

    public static void main(String[] args) {
        Thread ta = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (lock) {
                    while (letterCounter.get() < 26) {
                        while (mark != 1) {
                            lock.wait();
                        }
                        System.out.print("A:");
                        System.out.println( Character.toChars('a' + letterCounter.getAndIncrement()));
                        lock.notifyAll();
                        mark = 2;
                    }
                }
            }
        }, "线程A");


        Thread tb = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (lock) {
                    while (numCounter.get() < 26) {
                        while (mark != 2) {
                            lock.wait();
                        }
                        System.out.println("B:" + numCounter.getAndIncrement());
                        lock.notifyAll();
                        mark = 1;
                    }
                }
            }
        }, "线程B");

        ta.start();
        tb.start();
    }

}
