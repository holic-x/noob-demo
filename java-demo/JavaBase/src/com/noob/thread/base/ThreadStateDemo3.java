package com.noob.thread.base;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/4/20
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class ThreadStateDemo3 {
    public static void main(String[] args) throws InterruptedException {

        // 定义一个对象，用来加锁和解锁
        Object obj = new Object();

        // 定义一个线程，先抢占了obj对象的锁
        new Thread(() -> {
            synchronized (obj) {
                try {
                    Thread.sleep(100); // 第一个线程持有锁100毫秒
                    obj.wait();   // 通过wait()方法进行等待状态，并释放obj的对象锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 定义目标线程，获取等待获取obj的锁
        Thread thread = new Thread(() -> {
            System.out.println("2.执行thread.start()之后：" + Thread.currentThread().getState());
            synchronized (obj) {
                try {
                    Thread.sleep(100); // thread要持有对象锁100毫秒
                    obj.notify();// 通过notify()方法唤醒所有在obj上等待的线程继续执行后续操作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("4.阻塞结束后：" + Thread.currentThread().getState());
        });

        // 1.获取start()之前的状态
        System.out.println("1.通过new初始化一个线程,执行thread.start()之前：" + thread.getState());

        // 2.启动线程
        thread.start();

        // 3.先等100毫秒(第一个线程释放锁至少需要100毫秒，所以在第50毫秒时，thread正在因等待obj的对象锁而阻塞)
        Thread.sleep(50);
        System.out.println("3.因为等待锁而阻塞时：" + thread.getState());

        //再等300毫秒(两个线程的执行时间加上之前等待的50毫秒总共是250毫秒，所以在第300毫秒，所有的线程都已经执行完毕)
        Thread.sleep(300);
        System.out.println("5.线程执行完毕之后：" + thread.getState());

    }
}
