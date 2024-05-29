package com.noob.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 创建一个单线程的线程池
public class NewSingleThreadExecutorDemo {

    public static void main(String[] args) {
        ExecutorService es = Executors.newSingleThreadExecutor();
        // 模拟多线程操作
        for(int i=0;i<10;i++){
            int finalI = i;
            es.execute(new Runnable() {
                @Override
                public void run() {
                    // 模拟任务执行操作
                    System.out.println(Thread.currentThread().getName() + " 执行任务" + finalI) ;
                }
            });
        }
        es.shutdown();
    }
}
