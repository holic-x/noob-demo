package com.noob.algorithm.daily.thread;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 10.有500张余票，同时有4个购票窗口，模拟购票流程，打印购票结果
 * - 例如 从窗口1买入1张票，剩余499张票
 */
// 思路4：ReentrantLock 可重入锁
public class Solution010_06 {

    static int remindTickets = 20; // 余票定义
    static ReentrantLock lock = new ReentrantLock();

    // 窗口定义
    static class TicketWindow implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            // 模拟售票操作
            while (true) {
                lock.lock(); // 加锁
                Thread.sleep(100); // 模拟购票操作
                if (remindTickets <= 0) {
                    System.out.println(Thread.currentThread().getName() + "已售空...");
                    break; // 票已售空，窗口停止售票（终止while）
                } else {
                    remindTickets--; // 票数-1
                    System.out.println(Thread.currentThread().getName() + "售出1张票，现余票为" + remindTickets);
                }
                lock.unlock(); // 释放锁
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



