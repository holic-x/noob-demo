package com.noob.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/4/20
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class UseExecutorDemo2 implements Runnable{

    private  static AtomicInteger count =new AtomicInteger(0);

    @Override
    public void run() {
        try {
            int temp =count.incrementAndGet();
            System.out.println("任务:"+temp);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        LinkedBlockingQueue<Runnable> queue=new LinkedBlockingQueue<>();
        ExecutorService service =new ThreadPoolExecutor(
                5,
                10,
                120,
                TimeUnit.SECONDS,
                queue);

        for(int i=0;i<20;i++) {
            service.execute(new UseExecutorDemo2());
        }

        Thread.sleep(1000);
        System.out.println("queue size:"+queue.size());
    }
}