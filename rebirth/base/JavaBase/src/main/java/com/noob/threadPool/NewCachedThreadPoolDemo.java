package com.noob.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 创建一个可缓存的线程池
public class NewCachedThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
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
