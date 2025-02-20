package com.noob.algorithm.daily.thread;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 10.有500张余票，同时有4个购票窗口，模拟购票流程，打印购票结果
 * - 例如 从窗口1买入1张票，剩余499张票
 */
// 思路3：Semaphore
public class Solution010_05 {

    static int remindTickets = 20; // 余票定义
    static Semaphore semaphore = new Semaphore(1); // 信号量定义

    // 窗口定义
    static class TicketWindow implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            // 模拟售票操作
            while (true) {
                semaphore.acquire(); // 获取信号量
                Thread.sleep(100); // 模拟购票操作
                if (remindTickets <= 0) {
                    System.out.println(Thread.currentThread().getName() + "已售空...");
                    semaphore.release(); // 释放信号量
                    break; // 票已售空，窗口停止售票（终止while）
                } else {
                    remindTickets--; // 票数-1
                    System.out.println(Thread.currentThread().getName() + "售出1张票，现余票为" + remindTickets);
                }
                semaphore.release(); // 释放信号量
            }
        }
    }

    public static void main(String[] args) {
        // 创建4个窗口
        for (int i = 1; i <= 4; i++) {
            new Thread(new TicketWindow(), "窗口" + i).start();
        }
    }
}



