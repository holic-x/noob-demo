package com.noob.algorithm.daily.thread;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 10.有500张余票，同时有4个购票窗口，模拟购票流程，打印购票结果
 * - 例如 从窗口1买入1张票，剩余499张票
 */
public class Solution010_01 {

    static AtomicInteger remindTickets = new AtomicInteger(500); // 剩余500张余票

    @SneakyThrows
    // 定义购票方法
    public static void buyTicket() {
        // Thread.sleep(1000); // 模拟购票操作
        // 校验当前余票
        if (remindTickets.get() < 0) {
            System.out.println("当前余票不足....");
        } else {
            // 执行购票操作
            System.out.println(Thread.currentThread().getName() + "窗口售出1张票，剩余票数为:" + remindTickets.getAndDecrement());
        }
    }

    @SneakyThrows
    public static void main(String[] args) {

        // 模拟4个购票窗口（此处用线程池处理）
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // 模拟600个抢票操作
        for (int i = 0; i < 600; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    new Solution010_01().buyTicket();
                }
            });
        }

        // 关闭线程池
        executor.shutdown();

        // 等待线程执行完成
        executor.awaitTermination(1, TimeUnit.HOURS);

        System.out.println("售票结束......");

    }

}

