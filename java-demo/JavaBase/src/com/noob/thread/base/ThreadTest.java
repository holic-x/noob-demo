package com.noob.thread.base;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/3/25
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class ThreadTest {
    /**
     * 1>分别以两种方式测试线程创建，分析其生命周期
     * a.继承Thread类
     * b.实现Runnable接口
     *
     * 2>线程的5个状态分析
     * 新建     new
     * 就绪     ready
     * 运行     run
     * 阻塞     wait
     * 死亡     terminal（结束）
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 方式1创建线程:继承Thread
        MyThread1 mt = new MyThread1();
        mt.start();

        // 方式2创建线程:实现Runnable接口(可通过Lambda表达式创建并启动线程)
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.out.println("方式2创建线程:借助Lambda表达式构建");
                }
            }
        }).start();

        /**
         * 执行结果分析：线程的启动主要CPU内部的调度（涉及CPU时间片的概念）
         * 此处执行的结果显示，每次执行的线程顺序均有所不同，
         * 且每个线程是交替执行的，一个线程执行一段时间之后便将CPU交付给下一个线程执行以此类推
         */
    }
}
