package com.noob.multiThread.juc;

import java.util.concurrent.CountDownLatch;

// 案例：主线程需要等待5个子线程分别完成任务之后，再执行总任务
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            String taskName = "task_" + i;
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("完成任务：" + Thread.currentThread().getName());
                            countDownLatch.countDown();
                        }
                    }, taskName).start();
        }
        // 等待所有任务完成
        countDownLatch.await();
        System.out.println("所有任务完成");
    }
}
