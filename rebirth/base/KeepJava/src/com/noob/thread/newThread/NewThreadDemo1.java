package com.noob.thread.newThread;

/**
 * 创建线程的方式
 * 01-继承Thread类，重写run方法
 */
public class NewThreadDemo1{
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        // 启动线程
        myThread.start();
    }
}

// 自定义线程类
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread run ......");
    }
}
