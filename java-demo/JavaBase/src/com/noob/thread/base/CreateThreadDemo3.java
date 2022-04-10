package com.noob.thread.base;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/3/25
 * @Copyright： 无所事事是薄弱意志的避难所
 */
class MyThread3 implements Callable<String> {
    /**
     * 线程创建方式3：基于Callable
     * a.实现Callable接口
     * b.重写call方法
     * c.借助FutureTask对象创建间Thread对象，随后启动线程
     * d.调用get方法获取线程结束之后的结果
     */
    @Override
    public String call() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println("方式3:借助Callable创建线程" + i);
        }
        return "call线程";
    }
}

public class CreateThreadDemo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 线程开启之后需要执行里面的call方法
        MyThread3 mt = new MyThread3();
        // 构建FutureTask对象
        FutureTask<String> ft = new FutureTask<>(mt);
        //创建线程对象
        Thread t = new Thread(ft);
        // 开启线程
        t.start();
        System.out.println(ft.get());
    }
}
