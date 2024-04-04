package com.noob.thread.base;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/3/25
 * @Copyright： 无所事事是薄弱意志的避难所
 */
class CommonThread implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                System.out.println("#" + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 如果是后台线程(守护线程)则不执行finally语句
            System.out.println("finally语句执行");
        }
    }
}

public class SimpleDaemonDemo {

    public static void main(String[] args) throws InterruptedException {

        // 设置一个钩子线程，在JVM退出的时候输出日志
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("JVM exit ......");
        }));

        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new CommonThread());
            // Thread.sleep(1000);
            // 需在线程start之前调用setDaemon方法设置后台线程
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("All daemons started");
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}