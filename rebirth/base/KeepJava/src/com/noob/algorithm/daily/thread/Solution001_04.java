package com.noob.algorithm.daily.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1.写1个双线程轮流打印1-100
 */
public class Solution001_04 {
    private static final AtomicInteger number = new AtomicInteger(1);

    public static void main(String[] args) {
        new Thread(() -> {
            while (number.get() <= 100) {
                if (number.get() % 2 == 1) {
                    System.out.println("Thread 1: " + number.getAndIncrement());
                }
            }
        }).start();

        new Thread(() -> {
            while (number.get() <= 100) {
                if (number.get() % 2 == 0) {
                    System.out.println("Thread 2: " + number.getAndIncrement());
                }
            }
        }).start();
    }
}