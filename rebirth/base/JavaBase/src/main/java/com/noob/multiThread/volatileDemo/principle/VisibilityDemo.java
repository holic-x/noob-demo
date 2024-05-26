package com.noob.multiThread.volatileDemo.principle;

import java.util.concurrent.TimeUnit;

// 1.volatile关键字：可见性 案例分析(保证可见性的原理-volatile的内存语义)
public class VisibilityDemo {

//    private static boolean stop = false;
    private volatile static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        // thread A
        new Thread("Thread A"){
            @Override
            public void run() {
                while(!stop){
//                    System.out.println("==============");
                }
                System.out.println("3:"+Thread.currentThread().getName() + "停止了");
            }
        }.start();

        // thread main
        TimeUnit.SECONDS.sleep(1);
        System.out.println("1:主线程等待1s....");
        System.out.println("2:将stop遍历设置为true");
        stop = true;
    }
}
