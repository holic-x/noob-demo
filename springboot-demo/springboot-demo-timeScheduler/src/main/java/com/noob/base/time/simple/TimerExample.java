package com.noob.base.time.simple;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 传统方式①：基于Timer实现定时器
 */
public class TimerExample {

    public static void main(String[] args) throws InterruptedException {
        // 创建一个 Timer 实例
        Timer timer = new Timer();

        // 定义一个任务
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timer Task executed at: " + System.currentTimeMillis());
            }
        };

        // 安排任务：延迟 1 秒后执行，之后每隔 2 秒执行一次
        timer.schedule(task, 1000, 2000);

        // 让主线程休眠一段时间，以便观察定时任务的执行
        Thread.sleep(10000); // 主线程休眠 10 秒

        // 取消定时任务
        timer.cancel();
        System.out.println("Timer cancelled.");
    }
}