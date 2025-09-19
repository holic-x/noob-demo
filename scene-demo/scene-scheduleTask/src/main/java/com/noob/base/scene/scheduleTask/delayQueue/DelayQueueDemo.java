package com.noob.base.scene.scheduleTask.delayQueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;



/**
 * 延时队列案例示例
 */
public class DelayQueueDemo {

    public static void main(String[] args) {
        // 1.构建延时队列
        DelayQueue<MyTask> delayQueue = new DelayQueue<>();

        // 模拟添加延时任务
        delayQueue.add(new MyTask(1, "任务内容11111", 2000, TimeUnit.MILLISECONDS));
        delayQueue.add(new MyTask(2, "任务内容22222", 1000, TimeUnit.MILLISECONDS));
        delayQueue.add(new MyTask(3, "任务内容33333", 3000, TimeUnit.MILLISECONDS));
        delayQueue.add(new MyTask(4, "任务内容44444", 500, TimeUnit.MILLISECONDS));
        delayQueue.add(new MyTask(5, "任务内容55555", 2000, TimeUnit.MILLISECONDS));

        // 2.构建线程监听队列
        new Thread(() -> {
            System.out.println("定时任务启动......");
            while (true) {
                try {
                    // 从队列中获取任务到期的任务(take 方法内部实现了阻塞逻辑：从队列中获取到期的任务，如果没有到期的任务则阻塞等待，确保take拿到任务不为空)
                    MyTask task = delayQueue.take();
                    // 直接处理任务
                    System.out.println(String.format("处理任务-【%s】，处理内容-【%s】", task.getTaskId(), task.getTaskContent()));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

}
