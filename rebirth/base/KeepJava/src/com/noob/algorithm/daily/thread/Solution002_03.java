package com.noob.algorithm.daily.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution002_03 {

    static AtomicInteger count = new AtomicInteger(1); // 定义计数器
    // 信号量（分别定义三个信号量）
    static Semaphore firstToSecond = new Semaphore(1);// 初始化1个许可
    static Semaphore secondToThird = new Semaphore(0);
    static Semaphore ThirdToFirst = new Semaphore(0);

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                // 打印数字
                while (count.get() < 100) {
                    try {
                        // 获取许可
                        firstToSecond.acquire();
                        System.out.println("线程t1执行" + count.getAndIncrement());
                        // 释放许可
                        secondToThird.release();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                // 打印数字
                while (count.get() < 100) {
                    try {
                        // 值为1，获取许可，开始执行（如果值为0则进入阻塞状态）
                        secondToThird.acquire();
                        System.out.println("线程t2执行" + count.getAndIncrement());
                        // 释放许可
                        ThirdToFirst.release();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            public void run() {
                // 打印数字
                while (count.get() < 100) {
                    try {
                        // 值为1，获取许可，开始执行（如果值为0则进入阻塞状态）
                        ThirdToFirst.acquire();
                        System.out.println("线程t3执行" + count.getAndIncrement());
                        // 释放许可
                        firstToSecond.release();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        // 启动线程
        t1.start();
        t2.start();
        t3.start();
    }
}