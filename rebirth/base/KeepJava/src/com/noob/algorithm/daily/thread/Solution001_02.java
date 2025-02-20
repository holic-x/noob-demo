package com.noob.algorithm.daily.thread;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1.写1个双线程轮流打印1-100
 */
public class Solution001_02 {

    /**
     * 信号量：基于信号量实现互斥锁
     */
    static AtomicInteger counter = new AtomicInteger(1); // 定义全局计数器
    static Semaphore s1 = new Semaphore(1); // 初始化许可证为1
    static Semaphore s2 = new Semaphore(0); // 初始化许可证为0（S1、S2互斥）

    public static void main(String[] args) {

        // 定义线程A
        Thread ta = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                // 如果计数器未达到100则不断监听打印活动
                while (counter.get() < 100) {
                    s1.acquire(); // s1获取锁(许可证-1)
                    System.out.println("线程A:" + counter.getAndIncrement()); // 线程A申请到许可证，执行业务逻辑
                    s2.release();// s2释放锁(许可证+1)
                }
            }
        });

        // 定义线程B
        Thread tb = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                // 如果计数器未达到100则继续监听
                while (counter.get() < 100) {
                    s2.acquire(); // s2获取锁(许可证-1)
                    System.out.println("线程B:" + counter.getAndIncrement()); // 线程B申请到许可证，执行业务逻辑
                    s1.release(); // s1释放锁(许可证+1)
                }
            }
        });

        // 启动线程
        ta.start();
        tb.start();
    }
}
