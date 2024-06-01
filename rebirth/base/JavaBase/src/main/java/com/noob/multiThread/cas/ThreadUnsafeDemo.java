package com.noob.multiThread.cas;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class ThreadUnsafeOperator{

    // 定义一个共享数据
    // private static int count = 0; // 线程不安全的定义
    private AtomicInteger count = new AtomicInteger(0);

    // 对外提供数据操作方法
    public void add(){
        // count ++;
        count.getAndIncrement();
    }

    // 对外提供数据访问方法
    public int get(){
        // return count;
        return count.get();
    }

}

// 线程不安全示例：引入CAS解决
public class ThreadUnsafeDemo {

    // 借助并发工具类CountDownLatch(保证线程池完成500次累加)
    public static void modOpByCountDownLatch() throws InterruptedException {
        // 定义操作对象
        ThreadUnsafeOperator op = new ThreadUnsafeOperator();
        final int threadSize = 500;
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 模拟500个线程执行自增操作
        for(int i =0;i<threadSize;i++){
            executorService.execute(()->{
                // 调用方法执行自增操作
                op.add();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        // 获取最终count结果
        System.out.println("count:"+op.get());
    }

    public static void main(String[] args) throws InterruptedException {
        modOpByCountDownLatch();
    }
}
