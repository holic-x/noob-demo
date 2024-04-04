package com.noob.thread.base;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/3/25
 * @Copyright： 无所事事是薄弱意志的避难所
 */
class MyThread1 extends Thread {
    /**
     * 线程创建方式1：
     * a.继承Thread类
     * b.重写相应的run方法
     * c.调用start方法启动相应的线程
     */
    @Override
    public void run() {
        while(true){
            System.out.println("方式1创建线程......");
        }
    }
}

public class CreateThreadDemo1 {
    public static void main(String[] args) {
        // 方式1：继承Thread类(此类线程启动直接调用start方法)
        // 通过new关键字创建线程,此时线程ct1处于“新建new”状态
        MyThread1 mt = new MyThread1();
        // 线程ct1调用start方法,此时线程不是直接运行,而是处于“就绪ready”状态
        mt.start();
    }
}


