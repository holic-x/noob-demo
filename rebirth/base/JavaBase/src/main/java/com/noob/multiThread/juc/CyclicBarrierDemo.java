package com.noob.multiThread.juc;

import java.util.TreeMap;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

// 同步屏障 demo
public class CyclicBarrierDemo {

    public static void main(String[] args) throws Exception {
        CyclicBarrier cb = new CyclicBarrier(11);
        for (int i = 1; i <= 10; i++) {
            String taskName = "task_" + i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "正在等待...");
                try {
                    cb.await();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "完成任务");
            },taskName).start();
        }
        // 模拟延迟完成
        Thread.sleep(200);
        System.out.println("任务11还有5s，其他任务先等待...");
        Thread.sleep(5000);
        cb.await();
        System.out.println("主线程作为第11个线程，一起执行");
    }
}
