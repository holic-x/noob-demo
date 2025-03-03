package com.noob.base;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;


/**
 * Quartz 测试
 */
public class QuartzDemo {

    public static void main(String[] args) throws Exception {
        // 1.创建调度器 Scheduler
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();

        // 2.创建JobDetail实例，并与MyJob类绑定(Job执行内容)
        JobDetail job = JobBuilder.newJob(MyJob.class)
                .withIdentity("job1", "group1")
                .build();

        // 3.构建Trigger实例,每隔30s执行一次
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(30)
                        .repeatForever())
                .build();

        // 4.执行，开启调度器
        scheduler.scheduleJob(job, trigger);
        System.out.println(System.currentTimeMillis());
        scheduler.start();

        //主线程睡眠1分钟，然后关闭调度器
        TimeUnit.MINUTES.sleep(1);
        scheduler.shutdown();
        System.out.println(System.currentTimeMillis());
    }


}

class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("任务被执行: " + jobExecutionContext.getJobDetail().getKey());
    }
}
