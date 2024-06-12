package com.noob.multiThread.codes;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

// 思路1：==传统wait/notifyAll + 等待标记mark==
class ThreadDemo1_01 {

    static AtomicInteger count = new AtomicInteger(1); // 定义计数器
    static Object lock = new Object(); // 定义对象锁
    static int mark = 1; // 定义全局等待标记(1-线程A执行；2-线程B执行)

    public static void main(String[] args) {
        // 线程A定义
        Thread ta = new Thread(() -> {
            while (count.get() < 100) {
                synchronized (lock) {
                    while (mark != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("线程A执行" + count.getAndIncrement()); // 打印并执行自增操作
                    lock.notifyAll(); // 唤醒
                    mark = 2; // 切换等待标记
                }
            }
        });

        // 线程B定义
        Thread tb = new Thread(() -> {
            while (count.get() <= 100) {
                synchronized (lock) {
                    while (mark != 2) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("线程B执行" + count.getAndIncrement()); // 线程B打印信息
                    lock.notifyAll(); // 唤醒
                    mark = 1; // 切换等待标记
                }
            }
        });
        // 启动线程
        ta.start();
        tb.start();
    }
}

// 思路2：==信号量Semaphore==
class ThreadDemo1_02 {

    static AtomicInteger count = new AtomicInteger(1); // 定义计数器
    static Semaphore s1 = new Semaphore(1);
    static Semaphore s2 = new Semaphore(0);

    public static void main(String[] args) {
        // 线程A定义
        Thread ta = new Thread(() -> {
            try {
                while (count.get() < 100) {
                    s1.acquire();
                    System.out.println("线程A执行" + count.getAndIncrement());
                    s2.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 线程B定义
        Thread tb = new Thread(() -> {
            try {
                while (count.get() < 100) {
                    s2.acquire();
                    System.out.println("线程B执行" + count.getAndIncrement());
                    s1.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ta.start();
        tb.start();
    }
}

// 思路3：==LockSupport==
class ThreadDemo1_03 {
    static AtomicInteger count = new AtomicInteger(1); // 定义计数器
    static Thread[] threads = new Thread[2];

    public static void main(String[] args) {
        threads[0] = new Thread(() -> {
            while (count.get() < 100) {
                System.out.println("线程A执行" + count.getAndIncrement());
                LockSupport.unpark(threads[1]);
                LockSupport.park();
            }
        });

        threads[1] = new Thread(() -> {
            while (count.get() < 100) {
                LockSupport.park();
                System.out.println("线程B执行" + count.getAndIncrement());
                LockSupport.unpark(threads[0]);
            }
        });
        threads[0].start();
        threads[1].start();
    }
}

// 双线程打印分别打印计数偶数
class ThreadDemo1_04 {
    static AtomicInteger count = new AtomicInteger(1); // 定义计数器
    static Semaphore s1 = new Semaphore(1);
    static Semaphore s2 = new Semaphore(0);
    public static void main(String[] args) {
        // 线程A打印奇数
        Thread ta = new Thread(() -> {
            while (count.get() < 100) {
                while (count.get() % 2 == 1) {
                    try {
                        s1.acquire();
                        System.out.println("[奇数]线程A执行" + count.getAndIncrement());
                        s2.release();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        // 线程B打印偶数
        Thread tb = new Thread(() -> {
            while (count.get() <= 100) {
                while (count.get() % 2 == 0) {
                    try {
                        s2.acquire();
                        System.out.println("[偶数]线程B执行" + count.getAndIncrement());
                        s1.release();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        ta.start();
        tb.start();
    }
}

// 参考示例（奇数、偶数）



// 1.写一个双线程轮流打印1-100
public class ThreadDemo1 {


    public static void main(String[] args) {

    }

}
