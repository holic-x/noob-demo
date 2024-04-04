package com.noob.thread.base;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/3/25
 * @Copyright： 无所事事是薄弱意志的避难所
 */
class MyThread2 implements Runnable {
    /**
     * 线程创建方式2：
     * a.实现Runnable接口
     * b.重写run方法
     * c.调用start方法启动线程(需要通过Thread进行封装)
     */
    @Override
    public void run() {
        while (true) {
            System.out.println("方式2创建线程......");
        }
    }
}

public class CreateThreadDemo2 {
    public static void main(String[] args) {
        // 方式2：实现Runnable接口(此类线程不能直接调用start方法,需要通过Thread类进行封装再调用start方法启动线程)
        MyThread2 mt = new MyThread2();
        Thread thread = new Thread(mt);
        thread.start();
    }
}
