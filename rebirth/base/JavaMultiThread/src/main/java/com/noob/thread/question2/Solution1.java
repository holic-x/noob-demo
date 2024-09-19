package com.noob.thread.question2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 三线程轮流打印1-100
 * 解决思路1：对象锁+全局标识
 */
public class Solution1 {

    // 锁思路：对象锁+全局标识
    static Object lock = new Object();
    static AtomicInteger count = new AtomicInteger(1);
    static int mark = 1; // 定义等待标记（为1线程A打印，为2线程B打印；为3线程C打印）

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                // 打印数字
                while (count.get() < 100) {
                    synchronized (lock) {
                        while(mark !=1) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        // 排除101数字的打印
                        if(count.get() != 101) {
                            System.out.println("线程1执行" + count.getAndIncrement()); // 打印并执行自增操作
                        }else{
                            break; // 跳出当次循环
                        }
                        lock.notifyAll(); // 唤醒线程
                        mark = 2; // 切换等待标记
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                // 打印数字
                while (count.get() < 100) {
                    synchronized (lock) {
                        while(mark !=2) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        // 排除101数字的打印
                        if(count.get() != 101) {
                            System.out.println("线程2执行" + count.getAndIncrement()); // 打印并执行自增操作
                        }else{
                            break; // 跳出当次循环
                        }
                        lock.notifyAll(); // 唤醒线程
                        mark = 3; // 切换等待标记
                    }
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            public void run() {
                // 打印数字
                while (count.get() < 100) {
                    synchronized (lock) {
                        while(mark !=3) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        // 排除101数字的打印
                        if(count.get() != 101) {
                            System.out.println("线程3执行" + count.getAndIncrement()); // 打印并执行自增操作
                        }else{
                            break; // 跳出当次循环
                        }
                        lock.notifyAll(); // 唤醒线程
                        mark = 1; // 切换等待标记
                    }
                }
            }
        });

        // 启动线程
        t1.start();
        t2.start();
        t3.start();
    }
}
