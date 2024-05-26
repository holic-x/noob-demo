package com.noob.multiThread;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ThreadUnsafeOperator{

    // 定义一个共享数据
    private static int count = 0;

    // 对外提供数据操作方法
    public void add(){
        count ++;
    }

    // 对外提供数据访问方法
    public int get(){
        return count;
    }

}

/**
 * 多线程并发不安全
 */
public class ThreadUnsafeDemo {
    // 通过实现Runnable创建线程模拟多线程操作
    public static void modOpByRunnable(){
        // 定义操作对象
        ThreadUnsafeOperator op = new ThreadUnsafeOperator();
        final int threadSize = 100;

//        final int[] cur = {0};
        // 模拟500个线程执行自增操作
        for(int i =0;i<threadSize;i++){
            int finalI = i;
            new Thread(new Runnable(){
                @Override
                public void run() {
                    // 调用方法执行自增操作
                    op.add();
                    System.out.println("线程" + finalI + Thread.currentThread().getName());
//                    cur[0]++;
                }
            }).start();
        }
//        System.out.println("程序执行了" + cur[0] + "次");
        // 获取最终count结果
        System.out.println("count:"+op.get());
    }

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
        modOpByRunnable();
        System.out.println("------------------------------");
//        modOpByCountDownLatch();
    }
}
