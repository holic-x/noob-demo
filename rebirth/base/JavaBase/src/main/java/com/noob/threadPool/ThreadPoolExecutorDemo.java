package com.noob.threadPool;

import java.util.concurrent.*;

public class ThreadPoolExecutorDemo {

    // 自定义线程
    static class MyThread implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 执行");
        }
    }

    public static void main(String[] args) {
        // 创建线程池（核心线程数5，线程池最大数量10，线程空闲时存活时长500）
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 500, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 1000; i++) {
            threadPoolExecutor.execute(new MyThread());
            String info = String.format("线程池中线程数目：%s，队列中等待执行的任务数目：%s，已执行完别的任务数目：%s",
                    threadPoolExecutor.getPoolSize(),
                    threadPoolExecutor.getQueue().size(),
                    threadPoolExecutor.getCompletedTaskCount());
            System.out.println(info);
        }
        threadPoolExecutor.shutdown();
    }
}
