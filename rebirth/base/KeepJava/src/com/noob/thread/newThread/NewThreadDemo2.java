package com.noob.thread.newThread;

/**
 * 创建线程的方式
 * 02-实现Runnable接口，重写run方法（其启动还是要依赖于Thread）
 */
public class NewThreadDemo2 {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        // 启动线程（需借助Thread启动线程）
        new Thread(myRunnable).start();
    }
}

// 自定义线程类实现Runnable接口
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("MyRunnable run...");
    }
}
