package com.noob.thread.question9;

import java.util.concurrent.CountDownLatch;

/**
 * 问题：确保t1、t2、t3线程顺序执行（t1执行完后执行t2，然后执行t3）
 * 思路2：join + CountDownLatch 配合使用
 * 定义两个CountDownLatch计数器(await\countDown方法)，当t1Tot2Latch、t2Tot3Latch分别用于确保t1->t2、t2->t3的执行顺序
 * t1线程：执行t1任务，执行完成后计数器t1Tot2Latch减1（表示t1执行完成，可以继续下一步）
 * t2线程：校验t1是否执行完成（调用t1Tot2Latch.await()，如果还没执行完成则继续等待），执行t2并计数器t2Tot3Latch减1（表示t2执行完成，可以继续下一步）
 * t3线程：类似地，校验t2是否执行完成，执行t3
 * 最后通过join方法确保所有任务都执行完成，然后主线程才可以继续往下
 * 此处t1->t2->t3的内部同步执行顺序是由CountDownLatch保证的，而所有线程执行完成才执行主线程则是通过join方法进行控制
 */
public class Solution2 {

    public static void main(String[] args) throws InterruptedException {
        // 定义两个计数器
        CountDownLatch t1Tot2Latch = new CountDownLatch(1);// t1 -> t2 的同步（t1执行完成，计数器减1，才触发t2线程执行）
        CountDownLatch t2Tot3Latch = new CountDownLatch(1);// t2 -> t3 的同步（t2执行完成，计数器减1，才触发t3线程执行）

        // 创建3个任务(线程)
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("任务" + Thread.currentThread().getName() + "执行");
                    Thread.sleep(1000); // 模拟执行
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // t1执行完成，计数器减1
                t1Tot2Latch.countDown();
            }
        }, "A");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 等待计数器减为0才执行（此处即等待t1执行完成后计数器减1成功后才执行）
                    t1Tot2Latch.await();
                    System.out.println("任务" + Thread.currentThread().getName() + "执行");
                    Thread.sleep(1000); // 模拟执行
                    // t2执行完成，对应计数器减1
                    t2Tot3Latch.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "B");
        Thread t3 = new Thread(new Runnable() {
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

        // 所有线程执行完成，主线程继续往下
        System.out.println("所有任务执行完成");
    }
}

