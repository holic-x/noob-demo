package com.noob.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @description:Semaphore 基于线程池的案例
 * @author：holic-x
 * @date: 2018/4/20
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class SemaphorePoolDemo {
    public static void main(String[] args) {
        // 定义线程池
        ExecutorService service = Executors.newCachedThreadPool();
        // 定义信号量
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 10; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        // 获取许可
                        semaphore.acquire();
                        System.out.println("线程" + Thread.currentThread().getName() + ",进入,当前已经有" + (3 - semaphore.availablePermits()) + "个并发");
                        // 模拟业务操作
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程" + Thread.currentThread().getName() + ",即将离开....");
                        // 释放许可
                        semaphore.release();
                        System.out.println("线程" + Thread.currentThread().getName() + ",已离开,当前有" + (3 - semaphore.availablePermits()) + "个并发");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(runnable);
        }
    }
}
