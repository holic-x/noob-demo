package com.noob.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 02.2个线程交替打印奇偶数（1-100）
 */
public class Demo21 {

    // 思路：基于对象锁实现
    static AtomicInteger counter = new AtomicInteger(1); // 计数器定义
    static Object obj = new Object();

    public static void main(String[] args) {
        // 定义两个线程：线程A打印计数、线程B打印偶数
        Thread ta = new Thread(()->{
            while(counter.get()<100){
                // 加锁区域
                synchronized (obj){
                    // 线程A负责处理奇数的打印（如果当前值为奇数表示到它的轮次）
                    if(counter.get()%2==1){
                        System.out.println("线程A：" + counter.getAndIncrement());
                    }
                }
            }
        });
        Thread tb = new Thread(()->{
            while(counter.get()<100){
                // 加锁区域
                synchronized (obj){
                    // 线程B负责处理偶数的打印（如果当前值为偶数表示到它的轮次）
                    if(counter.get()%2==0){
                        System.out.println("线程B：" + counter.getAndIncrement());
                    }
                }
            }
        });

        // 启动线程
        ta.start();
        tb.start();
    }

}
