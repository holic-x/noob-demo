package com.noob.algorithm.daily.thread;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;

/**
 * 9.假设有t1、t2、t3 三个线程，如何保证t2在t1执行后执行、t3在t2执行后执行
 */
public class Solution009_02 {

    public static void main(String[] args) throws InterruptedException {
        // 定义两个计数器
        CountDownLatch t1Tot2Latch = new CountDownLatch(1);// t1 -> t2 的同步（t1执行完成，计数器减1，才触发t2线程执行）
        CountDownLatch t2Tot3Latch = new CountDownLatch(1);// t2 -> t3 的同步（t2执行完成，计数器减1，才触发t3线程执行）

        // 创建3个任务(线程)
        Thread t1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("任务" + Thread.currentThread().getName() + "执行");
                Thread.sleep(1000); // 模拟执行
                // t1执行完成，计数器减1
                t1Tot2Latch.countDown();
            }
        }, "A");

        Thread t2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                // 等待计数器减为0才执行（此处即等待t1执行完成后计数器减1成功后才执行）
                t1Tot2Latch.await();
                System.out.println("任务" + Thread.currentThread().getName() + "执行");
                Thread.sleep(1000); // 模拟执行
                // t2执行完成，对应计数器减1
                t2Tot3Latch.countDown();
            }
        }, "B");

        Thread t3 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                try {
                    // 等待计数器减为0才执行（此处即等待t2执行完成后计数器减1成功后才执行）
                    t2Tot3Latch.await();
                    System.out.println("任务" + Thread.currentThread().getName() + "执行");
                    Thread.sleep(1000); // 模拟执行
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "C");

        // 启动线程t1\t2\t3
        t1.start();
        t2.start();
        t3.start();

        // 等待所有线程执行完毕（此处t1\t2\t3的执行顺序由同步器进行控制，此处join的引入是为了确保t1\t2\t3都执行完成才继续主线程往下）
        t1.join();
        t2.join();
        t3.join();
        // 可以实验下，如果此处将join注释，则可能出现主线程内容输出了但是子线程还没执行完成，因此此处要将主线程阻塞，确保所有的任务执行完成才接着往下

        // 所有线程执行完成，主线程继续往下
        System.out.println("所有任务执行完成");
    }
}
