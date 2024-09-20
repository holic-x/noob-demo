package com.noob.thread.question1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 双线程轮流打印1-100
 * 解决思路2：信号量思路
 */
public class Solution2 {

    static AtomicInteger count = new AtomicInteger(1); // 定义计数器
    static Semaphore s1 = new Semaphore(1);
    static Semaphore s2 = new Semaphore(0);

    public static void main(String[] args) {
        // 分别定义两个线程用于打印数字
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while(count.get() < 100) {
                    try {
                        s1.acquire();
                        System.out.println("线程1执行"+count.getAndIncrement());
                        s2.release();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                while(count.get() < 100) {
                    try {
                        s2.acquire();
                        System.out.println("线程2执行"+count.getAndIncrement());
                        s1.release();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        // 启动线程
        t1.start();
        t2.start();
    }

}
