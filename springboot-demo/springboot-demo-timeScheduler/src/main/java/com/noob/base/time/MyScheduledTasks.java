package com.noob.base.time;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Springboot 的 @Scheduled 注解实现：
 * 1.启动配置中启动定时任务：@EnableScheduling
 * 2.创建类，定义定时任务（借助@Scheduled注解）
 * 3.其他配置参考application.yml
 */
// 定义定时任务
@Component
public class MyScheduledTasks {

    // 每隔5秒执行一次
    @Scheduled(fixedRate = 5000)
    public void taskWithFixedRate() {
        System.out.println("Fixed Rate Task :: Execution Time - " + System.currentTimeMillis());
    }

    // 每隔10秒执行一次，延迟5秒后开始执行
    @Scheduled(fixedDelay = 10000, initialDelay = 5000)
    public void taskWithFixedDelay() {
        System.out.println("Fixed Delay Task :: Execution Time - " + System.currentTimeMillis());
    }

    // 使用Cron表达式，每分钟的第30秒执行
    @Scheduled(cron = "30 * * * * ?")
    public void taskWithCronExpression() {
        System.out.println("Cron Task :: Execution Time - " + System.currentTimeMillis());
    }
}