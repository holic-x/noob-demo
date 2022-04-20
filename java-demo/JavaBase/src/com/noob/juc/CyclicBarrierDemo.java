package com.noob.juc;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author：holic-x
 * @date: 2022/4/20
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        ExecutorService service = Executors.newCachedThreadPool();
        Random r = new Random();
        // 三个人共同执行五个任务,只有当三个人同时到达,才能执行下一个任务
        for (int i = 0; i < 3; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        try {
                            Thread.sleep(r.nextInt(5000));
                            System.out.println(Thread.currentThread().getName() + "已到达" + (j + 1)
                                    + ",现在共有" + (cyclicBarrier.getNumberWaiting() + 1) + "个线程已到达");
                            if (cyclicBarrier.getNumberWaiting() + 1 == 3) {
                                System.out.println("所有线程都已经到达集合点，进行下一步操作...");
                            } else {
                                System.out.println("等待其他线程到达");
                            }
                            cyclicBarrier.await();
                        } catch (InterruptedException | BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

        }
        service.shutdown();
    }
}
