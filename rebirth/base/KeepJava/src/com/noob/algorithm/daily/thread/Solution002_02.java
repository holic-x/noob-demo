package com.noob.algorithm.daily.thread;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 2.三个线程顺序打出1-100
 */
public class Solution002_02 {

    // 信号量：实现互斥锁（确保只有1个许可证可用）
    static AtomicInteger counter = new AtomicInteger(1); // 定义计数器
    static Semaphore s1 = new Semaphore(1); // 初始化许可证为1
    static Semaphore s2 = new Semaphore(0); // 初始化许可证为0
    static Semaphore s3 = new Semaphore(0); // 初始化许可证为0


    public static void main(String[] args) {
        // 创建线程A
        Thread ta = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (counter.get() < 100) {
                    s1.acquire(); // 申请许可证 s1 许可证-1
                    System.out.println("线程A:" + counter.getAndIncrement());
                    s2.release(); // s2 许可证+1（s2释放锁）
                }
            }
        });

        // 创建线程B
        Thread tb = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (counter.get() < 100) {
                    s2.acquire(); // 申请许可证 s2 许可证-1
                    System.out.println("线程B:" + counter.getAndIncrement());
                    s3.release(); // s3 许可证+1（s3释放锁）
                }
            }
        });

        // 创建线程C
        Thread tc = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (counter.get() < 100) {
                    s3.acquire(); // 申请许可证 s3 许可证-1
                    System.out.println("线程B:" + counter.getAndIncrement());
                    s1.release(); // s1 许可证+1（s1释放锁）
                }
            }
        });

        // 启动线程
        ta.start();
        tb.start();
        tc.start();
    }

}
