package com.noob.thread.newThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建线程的方式
 * 04-线程池（借助ExecutorService、Executors 构建线程池，随后提交任务并执行）
 */
public class NewThreadDemo41 {
    public static void main(String[] args) {
        // 创建线程池(例如此处创建一个固定线程池大小的线程池，初始化值设定为10)
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new MyTask());
        }
        // 使用完毕关闭线程池
        executorService.shutdown();
    }
}

// 自定义任务
class MyTask implements Runnable{
    @Override
    public void run() {
        System.out.println("MyTask run:" + Thread.currentThread().getName());
    }
}
