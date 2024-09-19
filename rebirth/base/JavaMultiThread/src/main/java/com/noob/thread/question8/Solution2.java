package com.noob.thread.question8;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 双线程打印：一个打印abcd，一个打印1234，交替打印a1b2c3d4a1b2c3d4; 打印10轮
 */
public class Solution2 {
    // 定义对象锁
    static Object lock = new Object();

    // 定义打印标识（1打印字母；2打印数字）
    static int printMark = 1;

    // 定义字母打印计数
    static AtomicInteger letterCount = new AtomicInteger(0);
    // 定义数字打印计数
    static AtomicInteger numCount = new AtomicInteger(1);

    public static void main(String[] args) {
        // 定义线程1打印字母
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while(letterCount.get() < 40) {
                    synchronized (lock) {
                        while(printMark!=1){
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        // 执行打印、打印自增、切换标识、唤醒线程
                        System.out.println(Character.toChars('a' + letterCount.getAndIncrement()%4));
                        printMark=2;
                        lock.notifyAll();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                while(numCount.get() < 40) {
                    synchronized (lock) {
                        while(printMark!=2){
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        // 执行打印、计数自增(处理临界值)、切换标识、唤醒线程
//                        if(numCount.getAndIncrement()%4==0){
//                            System.out.println(4);
//                        }else{
                            System.out.println(numCount.getAndIncrement()%4);
//                        }
                        printMark=1;
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
