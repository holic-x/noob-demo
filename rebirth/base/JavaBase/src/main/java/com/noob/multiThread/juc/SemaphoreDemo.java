package com.noob.multiThread.juc;

import java.util.concurrent.Semaphore;
// 信号量 demo
public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {
        Semaphore s = new Semaphore(5);
        for (int i = 0; i < 20; i++) {
            String taskName = "task_" + i;
            new Thread(() -> {
                try {
                    s.acquire();
                    System.out.println("执行任务：" + Thread.currentThread().getName());
                    Thread.sleep(3000);
                    s.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, taskName).start();
        }
        Thread.sleep(20000);
    }
}
