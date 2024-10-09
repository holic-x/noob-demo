package com.noob.test;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 02.2个线程交替打印奇偶数（1-100）
 */
public class Demo22 {

    // 思路：基于信号量实现互斥锁
    static AtomicInteger counter = new AtomicInteger(1); // 计数器定义
    static Semaphore s1 = new Semaphore(1); // 初始化许可证为1
    static Semaphore s2 = new Semaphore(0); // 初始化许可证为0

    @SneakyThrows
    public static void main(String[] args) {
        // 定义两个线程：线程A打印计数、线程B打印偶数
        Thread ta = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (counter.get() < 100) {
                    // 线程A负责处理奇数的打印（如果当前值为奇数表示到它的轮次）
                    if (counter.get() % 2 == 1) {
                        s1.acquire(); // s1获取锁（许可证-1）
                        System.out.println("线程A：" + counter.getAndIncrement());
                        s2.release(); // s2释放锁（许可证+1）
                    }
                }
            }
        });
        Thread tb = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (counter.get() < 100) {
                    // 线程B负责处理偶数的打印（如果当前值为偶数表示到它的轮次）
                    if (counter.get() % 2 == 0) {
                        s2.acquire(); // s2获取锁（许可证-1）
                        System.out.println("线程B：" + counter.getAndIncrement());
                        s1.release(); // s1释放锁（许可证+1）
                    }
                }
            }
        });

        // 启动线程
        ta.start();
        tb.start();
    }

}
