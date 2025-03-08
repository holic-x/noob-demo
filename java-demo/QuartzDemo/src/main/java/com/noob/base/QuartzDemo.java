package com.noob.base;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzDemo {

    public static void main(String[] args) {
        try {
            // 创建调度器
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // 启动调度器
            scheduler.start();

            // 定义一个JobDetail，绑定到自定义的SimpleJob类
            JobDetail job = JobBuilder.newJob(SimpleJob.class)
                    .withIdentity("myJob", "group1")
                    .build();

            // 定义一个Trigger，每10秒触发一次
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("myTrigger", "group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(10)
                            .repeatForever())
                    .build();

            // 将Job和Trigger注册到调度器中
            scheduler.scheduleJob(job, trigger);

            // 让程序运行一段时间，以便观察输出
            Thread.sleep(60000);

            // 关闭调度器
            scheduler.shutdown();

        } catch (SchedulerException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}