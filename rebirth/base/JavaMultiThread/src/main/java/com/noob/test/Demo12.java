package com.noob.test;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 01.写一个双线程轮流打印1-100
 */
public class Demo12 {

    // 思路：信号量思路(基于信号量实现互斥锁)
    static AtomicInteger counter = new AtomicInteger(1); // 计数器
    static Semaphore s1 = new Semaphore(1); // 初始化许可证为1
    static Semaphore s2 = new Semaphore(0); // 初始化许可证为0

    public static void main(String[] args) {
        // 线程A定义
        Thread threadA = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (counter.get() < 100) {
                    s1.acquire(); // s1获取锁（许可证-1）
                    System.out.println("线程A：" + counter.getAndIncrement());
                    s2.release(); // s2释放锁（许可证+1）
                }
            }
        });

        // 线程B定义
        Thread threadB = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (counter.get() < 100) {
                    s2.acquire(); // s2获取锁（许可证-1）
                    System.out.println("线程B：" + counter.getAndIncrement());
                    s1.release(); // s1释放锁（许可证+1）
                }
            }
        });

        // 启动线程进行测试
        threadA.start();
        threadB.start();
    }
}
