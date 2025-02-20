package com.noob.algorithm.daily.thread;

import lombok.SneakyThrows;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1.写1个双线程轮流打印1-100
 */
public class Solution001_05 {

    private static AtomicInteger counter = new AtomicInteger(1);
    private static final BlockingQueue<Integer> queue1 = new LinkedBlockingQueue<>();
    private static final BlockingQueue<Integer> queue2 = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        Thread ta = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (counter.get() < 100) {
                    System.out.println(Thread.currentThread().getName() + ":" + queue1.take());
                    queue2.offer(counter.getAndIncrement());
                }
            }
        }, "线程A");

        Thread tb = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (counter.get() < 100) {
                    queue1.offer(counter.getAndIncrement());
                    System.out.println(Thread.currentThread().getName() + ":" + queue2.take());
                }
            }
        }, "线程B");

        ta.start();
        tb.start();
    }
}