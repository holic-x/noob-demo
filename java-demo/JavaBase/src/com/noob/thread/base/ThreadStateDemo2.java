package com.noob.thread.base;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/4/20
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class ThreadStateDemo2 {
    public static void main(String[] args) throws InterruptedException {

        // 定义一个对象，用来加锁和解锁
        Object obj = new Object();

        // 定义一个内部线程
        Thread thread = new Thread(() -> {
            System.out.println("2.执行thread.start()之后：" + Thread.currentThread().getState());
            synchronized (obj) {
                try {

                    //thread需要休眠100毫秒
                    Thread.sleep(100);

                    //thread100毫秒之后，通过wait()方法释放obj对象锁
                    obj.wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("4.被object.notify()方法唤醒之后：" + Thread.currentThread().getState());
        });

        // 1.获取start()之前的状态
        System.out.println("1.通过new初始化一个线程,执行start()之前：" + thread.getState());

        // 2.启动线程
        thread.start();

        // 3.main线程休眠150毫秒(因为thread在第100毫秒进入wait等待状态，所以第150秒肯定可以获取其状态)
        Thread.sleep(150);
        System.out.println("3.执行object.wait()时：" + thread.getState());

        // 定义另一个线程进行解锁
        new Thread(() -> {
            synchronized (obj) {
                // 唤醒等待的线程
                obj.notify();
            }
        }).start();

        // main线程休眠10毫秒等待thread线程能够苏醒
        Thread.sleep(10);

        // 获取thread运行结束之后的状态
        System.out.println("5.线程执行完毕之后：" + thread.getState() + "\n");

    }
}
