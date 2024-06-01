package com.noob.threadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// 线程池异常处理
public class ThreadPoolExceptionDemo {

    public static void handleByCatch() {
        ExecutorService es = Executors.newFixedThreadPool(1);
        es.submit(() -> {
            // 异常处理
            try {
                int res = 1 / 0;
            } catch (Exception e) {
                System.out.println("任务执行失败，请检查异常信息" + e.getMessage());
            }
        });
        es.shutdown();
    }

    public static void handleByFuture() throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<Boolean> future = es.submit(() -> {
            int res = 1 / 0;
            // lambda表达式中要有返回值，编译器才会将其识别为Callable（否则识别为Runnable则无法用Future）
            return true;
        });

        // 方法中如果出现异常，则调用Future的get()方法会返回这个异常，否则返回正常信息
        System.out.println("任务处理结果:" + future.get());
        es.shutdown();
    }

    public static void main(String[] args) throws Exception {
//        handleByCatch();
        handleByFuture();
    }


}
