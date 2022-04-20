package com.noob.thread.base;

/**
 * @description: volatile关键字应用
 * @author：holic-x
 * @date: 2018/4/20
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class ThreadVolatileDemo extends Thread {

    public volatile boolean isRunning = true;

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        System.out.println("进入run方法....");
        int a = 0;
        while (isRunning) {
            // ...
        }
        System.out.println("线程停止....");
    }

    public static void main(String[] args) throws Exception {
        ThreadVolatileDemo tv = new ThreadVolatileDemo();
        tv.start();
        Thread.sleep(1000);
        tv.setRunning(false);
        System.out.println("isRuning 的值已经被改变...false");
        Thread.sleep(1000);
        System.out.println(tv.isRunning);
    }

}