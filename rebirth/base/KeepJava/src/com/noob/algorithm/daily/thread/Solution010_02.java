package com.noob.algorithm.daily.thread;

import lombok.SneakyThrows;

public class Solution010_02 {

    // 定义对象锁
    static Object lock = new Object();

    // 剩余总票数
    static int remainTicket = 20;

    /**
     * 模拟购票窗口
     */
    static class TicketWindow implements Runnable {

        private String windowName;

        public TicketWindow(String windowName) {
            this.windowName = windowName;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                // 同步块：操作卖票操作
                synchronized (lock) {
                    if (remainTicket > 0) {
                        buyTicket();
                    } else {
                        System.out.println("票已售完，窗口" + this.windowName + "关闭");
                        break; // 票已售完，线程结束
                    }
                }
                // 模拟购票后的随机操作
                Thread.sleep(1000);
            }
        }

        /**
         * 购票
         */
        private void buyTicket() {
            System.out.println("窗口" + windowName + "售出一张票，当前余票" + remainTicket);
            remainTicket--;
        }
    }

    public static void main(String[] args) {
        // 开启4个窗口模拟售票
        for (int i = 1; i <= 4; i++) {
            new Thread(new TicketWindow("ticketWindow" + i)).start();
        }
    }
}