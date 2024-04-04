package com.noob.juc;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author：holic-x
 * @date: 2018/4/20
 * @Copyright： 无所事事是薄弱意志的避难所
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        new CountDownLatchDemo().init();
    }

    CountDownLatch cdl = new CountDownLatch(3);
    Random r = new Random();

    public void init() {
        // 模拟A线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("A同学正在赶来的路上....");
                    Thread.sleep(r.nextInt(5000));
                    System.out.println("A同学已经到达集合点....");
                    cdl.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A同学").start();

        // 模拟B线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("B同学正在赶来的路上....");
                    Thread.sleep(r.nextInt(5000));
                    System.out.println("B同学已经到达集合点....");
                    cdl.countDown();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B同学").start();

        // 模拟C线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("C同学正在赶来的路上....");
                    Thread.sleep(r.nextInt(5000));
                    System.out.println("C同学已经到达集合点....");
                    cdl.countDown();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "C同学").start();

        // 模拟父线程(在cdl计数到达0之前线程一直阻塞)
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("司机正等待所有同学到达集合点...");
                    cdl.await();// 在计数达到0之前当前线程一直阻塞
                    System.out.println("所有的同学都已经到达，出发...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "司机叔叔").start();
    }
}
