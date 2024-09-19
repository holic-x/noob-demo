package com.noob.thread.question5;

import java.lang.management.ThreadMXBean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程交叉打印12A34B56C，多种实现方式(一个打印数字，一个打印字母)
 */
public class Solution1 {

    // 定义对象锁
    static Object lock = new Object();
    // 定义打印标识（线程1打印数字、线程2打印字母）
    static int printMark = 1; // 1打印数字、2打印字母

    static AtomicInteger numCount = new AtomicInteger(1); // 定义数字计数器
    static AtomicInteger letterCount = new AtomicInteger(0);// 定义字母计数器
    static char[] letters ={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    public static void main(String[] args) {

        // 定义线程1 打印数字（52个数字）
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while(numCount.get()<=52){
                    synchronized(lock){
                        while (printMark!=1){
                            // 非当前轮次则等待（并非打印数字）
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        // 轮到打印数字，执行操作，切换标志。唤醒其他线程
                        System.out.println(numCount.getAndIncrement());
                        System.out.println(numCount.getAndIncrement());
                        printMark = 2;
                        lock.notifyAll();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                while(letterCount.get()<26){
                    synchronized(lock){
                        while (printMark!=2){
                            // 非当前轮次则等待（并非打印字母）
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        // 轮到打印字母，执行操作，切换标志。唤醒其他线程
                        System.out.println(letters[letterCount.getAndIncrement()]);
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
