package com.noob.multiThread.synchronizedDemo;

// 1.对于普通同步方法
class SynchronizedMethod implements Runnable {

    @Override
    public void run() {
        method();
    }

    // 定义普通同步方法
    public synchronized void method(){
        System.out.println("线程" + Thread.currentThread().getName() + " start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("线程" + Thread.currentThread().getName() + " end");
    }

    public static void main(String[] args) {
        // 创建两个线程模拟
        /*
        SynchronizedMethod sm = new SynchronizedMethod();
        Thread t1 = new Thread(sm);
        Thread t2 = new Thread(sm);
        t1.start();
        t2.start();
         */

        // 模拟多个线程执行
        final int threadSize = 100;
        SynchronizedMethod sm = new SynchronizedMethod();
        for (int i = 0; i < threadSize; i++) {
            new Thread(sm).start();
        }
    }
}


// 2.对于同步方法块
class SynchronizedBlock implements Runnable {

    // 指定对象
    Object lockTarget = new Object();

    @Override
    public void run() {
        // 同步方法块
        synchronized (lockTarget){
            System.out.println("线程" + Thread.currentThread().getName() + " start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("线程" + Thread.currentThread().getName() + " end");
        }
    }

    public static void main(String[] args) {
        // 模拟多个线程执行
        final int threadSize = 100;
        SynchronizedBlock sb = new SynchronizedBlock();
        for (int i = 0; i < threadSize; i++) {
            new Thread(sb).start();
        }
    }
}

// 3.对于静态同步方法
class SynchronizedStaticMethod implements Runnable {

    @Override
    public void run() {
        method();
    }

    // 定义普通同步方法
    public static synchronized void method(){
        System.out.println("线程" + Thread.currentThread().getName() + " start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("线程" + Thread.currentThread().getName() + " end");
    }

    public static void main(String[] args) {
        // 模拟多个线程执行
        SynchronizedStaticMethod ssm1 = new SynchronizedStaticMethod();
        SynchronizedStaticMethod ssm2 = new SynchronizedStaticMethod();
        new Thread(ssm1).start();
        new Thread(ssm2).start();
    }
}




/**
 * synchronized 关键字应用
 */
public class SynchronizedDemo {


}
