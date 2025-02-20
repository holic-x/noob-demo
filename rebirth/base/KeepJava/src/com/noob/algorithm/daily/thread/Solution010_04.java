package com.noob.algorithm.daily.thread;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 10.有500张余票，同时有4个购票窗口，模拟购票流程，打印购票结果
 * - 例如 从窗口1买入1张票，剩余499张票
 */
// 思路2：AtomicInteger
public class Solution010_04 {

    static AtomicInteger remindTickets = new AtomicInteger(20); // 余票定义

    // 窗口定义
    static class TicketWindow implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            // 模拟售票操作
            while (true) {
                Thread.sleep(1000); // 模拟购票操作
                if (remindTickets.get() <= 0) {
                    System.out.println(Thread.currentThread().getName() + "已售空...");
                    break; // 票已售空，窗口停止售票（终止while）
                } else {
                    System.out.println(Thread.currentThread().getName() + "售出1张票，现余票为" + remindTickets.getAndDecrement());
                }
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



