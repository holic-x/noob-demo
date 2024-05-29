package com.noob.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 创建一个固定大小的线程池
public class NewFixedThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            es.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " 执行任务" + finalI);
                }
            });
        }
        es.shutdown();
    }

}
