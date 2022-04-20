package com.noob.thread.base;

/**
 * @description: 线程:TIME_WAITING状态装换
 * @author：holic-x
 * @date: 2018/4/20
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class ThreadStateDemo1 {

    public static void main(String[] args) throws InterruptedException {

        // 定义一个内部线程
        Thread thread = new Thread(() -> {
            System.out.println("2.执行thread.start()之后：" + Thread.currentThread().getState());
            try {
                // 休眠100毫秒
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("4.执行Thread.sleep(long)完成之后：" + Thread.currentThread().getState());
        });

        // 1.获取start()之前的状态
        System.out.println("1.通过new初始化一个线程,调用start()之前：" + thread.getState());

        // 2.启动线程
        thread.start();

        // 3.休眠50毫秒(因为thread需要休眠100毫秒，所以在第50毫秒，thread处于sleep状态)
        Thread.sleep(50);
        System.out.println("3.执行Thread.sleep(long)时：" + thread.getState());

        // thread和main线程主动休眠150毫秒，所以在第150毫秒,thread早已执行完毕
        Thread.sleep(100);

        System.out.println("5.线程执行完毕之后：" + thread.getState() + "\n");

    }

}
