package com.noob.base.time.simple;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 传统方式②：基于ScheduledExecutorService实现定时任务
 */
public class ScheduledExecutorServiceExample {

    public static void main(String[] args) throws InterruptedException {
        // 创建一个 ScheduledExecutorService 实例
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // 定义一个任务
        Runnable task = () -> {
            System.out.println("ScheduledExecutorService Task executed at: " + System.currentTimeMillis());
        };

        // 安排任务：延迟 1 秒后执行，之后每隔 2 秒执行一次
        scheduler.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);

        // 让主线程休眠一段时间，以便观察定时任务的执行
        Thread.sleep(10000); // 主线程休眠 10 秒

        // 关闭调度器
        scheduler.shutdown();
        System.out.println("Scheduler shutdown.");
    }
}