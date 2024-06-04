package com.noob.jvm;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 死锁案例场景
 */
public class DeadLockDemo extends Thread {

    private String first;
    private String second;
    public DeadLockDemo(String threadName,String first, String second) {
        super(threadName);
        this.first = first;
        this.second = second;
    }

    // 重写run方法
    @Override
    public void run() {
        // 使用synchronized嵌套获取锁
        synchronized (first){
            System.out.println(this.getName() + "obtained：" + first);
            // 模拟等待时长
            try {
                Thread.sleep(1000L);
                // 嵌套Synchronized获取锁
                synchronized (second){
                    System.out.println(this.getName() + "obtained:" + second );
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 死锁测试
    public static void main(String[] args) throws InterruptedException {
        ThreadMXBean mbean = ManagementFactory.getThreadMXBean();
        Runnable dlCheck = new Runnable() {
            @Override
            public void run() {
                long[] threadIds = mbean.findDeadlockedThreads();
                if (threadIds != null) {
                    ThreadInfo[] threadInfos = mbean.getThreadInfo(threadIds);
                    System.out.println("Detected deadlock threads:");
                    for (ThreadInfo threadInfo : threadInfos) {
                        System.out.println(threadInfo.getThreadName());
                    }
                }
            }
        };

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        // 稍等5秒，然后每10秒进行一次死锁扫描
        scheduler.scheduleAtFixedRate(dlCheck, 5L, 10L, TimeUnit.SECONDS);

        // ---------- 死锁样例代码 ----------
        String lockA = "lockA";
        String lockB = "lockB";
        Thread threadA = new DeadLockDemo("threadA",lockA,lockB);
        Thread threadB = new DeadLockDemo("threadB",lockB,lockA);
        // 启动线程
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }
}
