package com.noob.algorithm.daily.thread;

import java.util.concurrent.Semaphore;

public class TicketSystemSemaphore {
    private static int tickets = 100; // 总票数
    private static final Semaphore semaphore = new Semaphore(1); // 信号量

    public static void main(String[] args) {
        // 创建4个购票窗口（线程）
        for (int i = 1; i <= 4; i++) {
            new Thread(new TicketWindow(i), "窗口" + i).start();
        }
    }

    // 购票窗口任务
    static class TicketWindow implements Runnable {
        private final int windowId; // 窗口编号

        public TicketWindow(int windowId) {
            this.windowId = windowId;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    semaphore.acquire(); // 获取信号量
                    if (tickets > 0) {
                        System.out.println("窗口" + windowId + "售出第" + tickets + "张票");
                        tickets--;
                    } else {
                        System.out.println("窗口" + windowId + "发现票已售罄");
                        semaphore.release(); // 释放信号量
                        break;
                    }
                    semaphore.release(); // 释放信号量
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(100); // 模拟购票时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}