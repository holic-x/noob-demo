package com.noob.algorithm.daily.thread;

import lombok.SneakyThrows;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1.写1个双线程轮流打印1-100
 */
public class Solution001_06 {

    static int counter = 1;
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    static int mark = 1;

    public static void main(String[] args) {
        Thread ta = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (counter < 100) {
                    // 加锁
                    lock.lock();

                    while (mark != 1) {
                        condition.await(); // 等待唤醒
                    }

                    // 实现逻辑
                    System.out.println(Thread.currentThread().getName() + ":" + (counter++));
                    condition.signal(); // 唤醒其他线程
                    mark = 2; // 切换打印标识

                    // 解锁
                    lock.unlock();

                }
            }
        }, "线程A");

        Thread tb = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (counter < 100) {
                    // 加锁
                    lock.lock();

                    while (mark != 2) {
                        condition.await(); // 等待唤醒
                    }

                    // 实现逻辑
                    System.out.println(Thread.currentThread().getName() + ":" + (counter++));
                    condition.signal(); // 唤醒其他线程
                    mark = 1; // 切换打印标识

                    // 解锁
                    lock.unlock();

                }
            }
        }, "线程B");

        ta.start();
        tb.start();
    }
}