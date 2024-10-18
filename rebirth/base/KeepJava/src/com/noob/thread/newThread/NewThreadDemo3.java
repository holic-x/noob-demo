package com.noob.thread.newThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的方式
 * 03-使用Callable接口，结合FutureTask使用（可处理回调结果），需借助Thread启动线程
 */
public class NewThreadDemo3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建MyCallable对象
        MyCallable myCallable = new MyCallable();
        // 启动线程（搭配FutureTask处理回调结果，借助Thread启动线程）
        FutureTask<Integer> futureTask = new FutureTask<Integer>(myCallable);
        new Thread(futureTask).start();
        System.out.println("调度处理返回结果：" + futureTask.get());
    }
}

// 自定义类实现Callable
class MyCallable implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println("MyCallable call .....");
        return "hello world";
    }
}
