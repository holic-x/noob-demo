package com.noob.test;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 10.模拟购票：500张票，有4个购票窗口，模拟购票流程，打印购票结果
 */
public class Demo101 {

    // 定义余票（起始剩余500）
    static AtomicInteger remainTicket = new AtomicInteger(500);

    // 定义对象锁
    static Object lock = new Object();

    // 定义购票方法
    @SneakyThrows
    public static void buyTicket() {
        // 模拟购票耗时操作
        Thread.sleep(1000);
        remainTicket.getAndDecrement();
    }

    // 在线程的run方法中控制同步代码块
    public static void main(String[] args) {
        // 4个购票窗口，模拟购票流程（定义4个线程操作）
        for (int i = 1; i <= 4; i++) {
            int finalI = i;
            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        synchronized (lock) {
                            if (remainTicket.get() > 0) {
                                Demo101.buyTicket(); // 调用卖票方法
                                System.out.println("当前窗口" + finalI + "-模拟购票1张，当前剩余" + remainTicket.get());
                            } else {
                                System.out.println("当前窗口" + finalI + "-票已经售罄....");
                                break; // 跳出循环，结束线程
                            }
                        }
                    }
                }
            });
            // 启动线程
            t.start();
        }
    }

}
