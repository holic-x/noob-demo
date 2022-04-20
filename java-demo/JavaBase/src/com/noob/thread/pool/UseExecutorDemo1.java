package com.noob.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 线程池使用
 * @author：holic-x
 * @date: 2018/4/20
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class UseExecutorDemo1 {
    public static void main(String[] args) {
        //开启是十个任务  每个任务是循环十次
        //创建一个线程池
        ExecutorService pool = Executors.newSingleThreadExecutor();
        //创建可调整的线程池
        ExecutorService pool2 =Executors.newCachedThreadPool();
        //创建指定线程的线程池
        ExecutorService pool3 =Executors.newFixedThreadPool(10);

        //开启十个任务
        for(int i =0;i<10;i++) {
            final int task=i;
            //把这个十个任务放到线程池中
            pool3.execute(new Runnable() { // 通过execute或者submit执行任务
                @Override
                public void run() {
                    //每个线程是循环十次
                    for(int  k=0;k<10;k++) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("当前线程的名称是:"+Thread.currentThread().getName()+"运行第"+task+"个任务");
                    }

                }
            });
        }

        //关闭线程池
        pool3.shutdown();
    }
}
