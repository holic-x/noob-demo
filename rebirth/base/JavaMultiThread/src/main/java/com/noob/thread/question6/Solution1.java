package com.noob.thread.question6;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 两个线程交替打印ABCD..Z字母，一个大写一个小写
 * 思路：回归双线程打印思路
 */
public class Solution1 {

    // 定义对象锁
    static Object lock = new Object();
    // 定义打印标识
    static int printMark = 1; // 1打印大写、2打印小写

    static AtomicInteger letterCount = new AtomicInteger(0);// 定义字母计数器
    static char[] letters ={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    public static void main(String[] args) {
        // 定义线程打印大写字母
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while (letterCount.get()<25) {
                    synchronized (lock) {
                        // 判断是否为当前轮次（打印大写字母）
                        while(printMark != 1){
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        // 打印大写字母，计数+1，切换标志，唤醒线程
                        System.out.println(letters[letterCount.getAndIncrement()]);
                        printMark = 2;
                        lock.notifyAll();
                    }
                }
            }
        });
        // 定义线程打印小写字母
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                while (letterCount.get()<26) {
                    synchronized (lock) {
                        // 判断是否为当前轮次
                        while(printMark != 2){
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        // 打印小写字母，计数+1，切换标志，唤醒线程
                        System.out.println(Character.toLowerCase(letters[letterCount.getAndIncrement()]));
                        printMark = 1;
                        lock.notifyAll();
                    }
                }
            }
        });
        // 启动线程
        t1.start();
        t2.start();
    }

}
