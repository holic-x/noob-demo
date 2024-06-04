package com.noob.jvm;




/**
 * 线程死锁Demo
 */
public class ThreadDeadLockDemo {

    // 定义锁
    private static Object lockA = new Object();
    private static Object lockB = new Object();

    public static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (lockA){
                System.out.println(Thread.currentThread().getName() + "占据了锁A");
                synchronized (lockB){
                    System.out.println(Thread.currentThread().getName() + "占据了锁B");
                }
            }
        }
    }

    public static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "占据了锁B");
                synchronized (lockA){
                    System.out.println(Thread.currentThread().getName() + "占据了锁A");
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();
        t1.start();
        t2.start();
    }
}
