package com.noob.base.scene.scheduleTask.delayQueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务调度器
 */
public class ScheduleTaskProcessor implements Runnable {
    // 定时任务开关
    private volatile boolean running;

    // 延时队列
    private DelayQueue<MyTask> delayQueue;

    public ScheduleTaskProcessor(boolean running) {
        this.running = running;
        this.delayQueue = new DelayQueue<>();
    }

    // 添加任务
    public void addTask(MyTask task) {
        this.delayQueue.add(task);
    }

    // 停止定时器
    public void stop() {
        this.running = false;
    }

    @Override
    public void run() {
        System.out.println("定时任务处理器开启......");
        // 监听定时任务开关
        while (running) {
            try {
                // 从队列中获取任务到期的任务(take 方法内部实现了阻塞逻辑：从队列中获取到期的任务，如果没有到期的任务则阻塞等待，确保take拿到任务不为空)
                MyTask task = delayQueue.take();
                // 直接处理任务
                System.out.println(String.format("处理任务-【%s】，处理内容-【%s】", task.getTaskId(), task.getTaskContent()));
            } catch (InterruptedException e) {
                if (!running) {
                    break;
                }
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("定时任务处理器停止......");
    }

    public static void main(String[] args) throws InterruptedException {
        // 1.创建定时任务处理器
        ScheduleTaskProcessor processor = new ScheduleTaskProcessor(true);

        // 2.添加任务
        processor.addTask(new MyTask(1, "任务内容11111", 2000, TimeUnit.MILLISECONDS));
        processor.addTask(new MyTask(2, "任务内容22222", 1000, TimeUnit.MILLISECONDS));
        processor.addTask(new MyTask(3, "任务内容33333", 3000, TimeUnit.MILLISECONDS));
        processor.addTask(new MyTask(4, "任务内容44444", 500, TimeUnit.MILLISECONDS));
        processor.addTask(new MyTask(5, "任务内容55555", 2000, TimeUnit.MILLISECONDS));

        // 3.启动定时器
        new Thread(processor).start();

        // 4.模拟等待1s后停止定时器
        Thread.sleep(1000);
        processor.stop();
    }
}
