package com.noob.thread.newThread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建线程方式
 * 05-CompletableFuture方式
 */
public class CompletableFutureDemo {

    // CompletableFuture 基础使用案例
    public static void test01() throws ExecutionException, InterruptedException {
        // 定义程序执行开始时间
        long start = System.currentTimeMillis();

        // 有返回值的任务(设定此处返回String，通过Lambda表达式简化任务定义)
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            // 模拟任务执行
            executeTask("cf1", 1);
            return "hello world";
        });

        // 无返回值的任务
        CompletableFuture cf2 = CompletableFuture.runAsync(() -> {
            executeTask("cf2", 2);
        });

        // 任务编排：此处设定阻塞等待所有异步任务执行结束(即并行执行cf1、cf2，主线程阻塞等待所有任务结束)
        CompletableFuture.allOf(cf1, cf2).get();

        // 定义程序执行结束时间
        long end = System.currentTimeMillis();
        System.out.println("本次执行消耗时间为：" + (end - start) + "ms");
    }

    // CompletableFuture 主线程、守护线程 说明（验证默认情况下，如果不指定线程池，异步任务的线程采用的是通过ForkJoinPool创建的子线程，是守护线程）
    public static void test02() throws ExecutionException, InterruptedException {

        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            // 模拟任务执行
            executeTask("cf", 2);
            // 判断当前线程是否为守护线程
            boolean isDaemon = Thread.currentThread().isDaemon();
            System.out.println(Thread.currentThread().getName() + (isDaemon ? "是" : "不是") + "守护线程");
        });

        // 主线程等待任务执行
        CompletableFuture.allOf(cf).get();
        System.out.println("任务执行完成");

    }

    // CompletableFuture 守护线程生命周期案例理解
    public static void test03() throws ExecutionException, InterruptedException {

        // 记录线程执行时间
        long start = System.currentTimeMillis();

        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            // 模拟任务执行（执行5s）
            executeTask("cf", 5);
            // 判断当前线程是否为守护线程
            boolean isDaemon = Thread.currentThread().isDaemon();
            System.out.println(Thread.currentThread().getName() + (isDaemon ? "是" : "不是") + "守护线程");
        });

        // 主线程等待任务执行
        CompletableFuture.allOf(cf); // 此处没有调用get()方法进行阻塞

        // 主线程模拟耗时（验证守护线程的生命周期）
        // executeTask("主线程",10);

        long end = System.currentTimeMillis();
        System.out.println("任务执行完成,共计耗时：" + (end - start) + "ms");
    }

    // 自定义线程 属性验证
    public static void test04() throws ExecutionException, InterruptedException {

        // 记录线程执行时间
        long start = System.currentTimeMillis();

        // 自定义普通线程
        new Thread(() -> {
            boolean isDaemon = Thread.currentThread().isDaemon();
            System.out.println(Thread.currentThread().getName() + (isDaemon ? "是" : "不是") + "守护线程");
            executeTask("我是自定义线程", 5); // 模拟普通线程执行5s
        }).start();

        long end = System.currentTimeMillis();
        System.out.println("任务执行完成,共计耗时：" + (end - start) + "ms");
    }

    // CompletableFuture 自定义线程池替换CompletableFuture默认的ForkJoinPool
    public static void test05() throws ExecutionException, InterruptedException {

        // 通过ExecutorService创建线程池
        ExecutorService es = Executors.newFixedThreadPool(10);

        // 记录线程执行时间
        long start = System.currentTimeMillis();

        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            // 判断当前线程是否为守护线程
            boolean isDaemon = Thread.currentThread().isDaemon();
            System.out.println(Thread.currentThread().getName() + (isDaemon ? "是" : "不是") + "守护线程");
            // 模拟任务执行（执行5s）
            executeTask("cf", 5);
            System.out.println("当前任务执行完成");
        },es);

        // 主线程等待任务执行
        CompletableFuture.allOf(cf); // 此处没有调用get()方法进行阻塞，而是使用自定义线程池替换原有的默认的ForkJoinPool

        // 任务执行完成，关闭线程池
        es.shutdown();

        long end = System.currentTimeMillis();
        System.out.println("任务执行完成,共计耗时：" + (end - start) + "ms");
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test02();
//        test03();
//        test04();
        test05();
    }

    public static void executeTask(String taskType, int seconds) {
        System.out.println(taskType + "模拟任务执行，沉睡指定秒数" + seconds + "s");
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
