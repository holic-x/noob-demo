package com.noob.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// 创建一个大小无限的线程池
public class NewScheduleThreadPoolDemo {
    public static void schedule(){
        ScheduledExecutorService ses  = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            ses.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " 执行任务" + finalI);
                }
            },1, TimeUnit.SECONDS);
        }
        ses.shutdown();
    }

    public static void scheduleAtFixedRate(){
        ScheduledExecutorService ses  = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            ses.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " 执行任务" + finalI);
                }
            },1,1, TimeUnit.SECONDS);
        }
        ses.shutdown();
    }

    public static void main(String[] args) {
        schedule();
//        scheduleAtFixedRate();
    }
}
