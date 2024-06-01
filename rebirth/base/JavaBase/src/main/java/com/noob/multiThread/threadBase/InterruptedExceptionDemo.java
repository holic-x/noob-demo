package com.noob.multiThread.threadBase;


import java.util.concurrent.TimeUnit;

class MyThread extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(20000);
            System.out.println("MyThread run");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("catch:" + Thread.currentThread().isInterrupted());
        }

    }
}

/**
 * InterruptedException demo
 */
public class InterruptedExceptionDemo {
    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
    }
}
