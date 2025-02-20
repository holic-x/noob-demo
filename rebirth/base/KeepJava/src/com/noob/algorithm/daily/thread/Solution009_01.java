package com.noob.algorithm.daily.thread;

import lombok.SneakyThrows;

/**
 * 9.假设有t1、t2、t3 三个线程，如何保证t2在t1执行后执行、t3在t2执行后执行
 */
public class Solution009_01 {

    @SneakyThrows
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyTask("A"));
        Thread t2 = new Thread(new MyTask("B"));
        Thread t3 = new Thread(new MyTask("C"));

        // 调用join方法的线程执行完成才会继续往下执行，否则会继续阻塞主线程

        // 启动线程t1，调用join方法等待线程t1执行完成
        t1.start();
        t1.join();
        // 继续启动线程t2、调用join方法等待线程t2执行完成
        t2.start();
        t2.join();
        // 继续启动线程t3、调用join方法等待线程t3执行完成
        t3.start();
        t3.join();

        // 所有线程执行完成，主线程继续往下
        System.out.println("所有任务执行完成");
    }
}

// 自定义任务
class MyTask implements Runnable {
    String taskName;

    MyTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println(taskName + "执行任务......");
    }
}