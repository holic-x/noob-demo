package com.noob.juc;

import java.util.concurrent.Semaphore;

/**
 * @description: Semaphore demo测试
 * @author：holic-x
 * @date: 2018/4/20
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        new SemaphoreDemo().init();
    }

    DoSth ds = new DoSth();

    public void init() {
        // 形参接收的是允许的许可数
        Semaphore semaphore = new Semaphore(2);

        for (int i = 0; i < 10; i++) {
            final int temp = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 在进行工作之前必须有许可
                    try {
                        // 获取许可
                        semaphore.acquire();
                        // 模拟业务操作
                        ds.work();
                        semaphore.release();// 释放许可
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }, "线程" + i).start();
        }
    }

    class DoSth {
        public void work() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前线程名称是:" + Thread.currentThread().getName());
        }
    }

}