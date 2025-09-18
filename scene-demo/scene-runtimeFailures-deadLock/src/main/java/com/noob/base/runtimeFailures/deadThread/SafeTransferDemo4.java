package com.noob.base.runtimeFailures.deadThread;

import java.util.ArrayList;
import java.util.List;

/**
 * 死锁案例：转账场景
 */
public class SafeTransferDemo4 {

    // 转账方法
    public static void transfer(Account from, Account to, int amount) {
        // 先根据账号ID进行排序，明确锁顺序
        Object first = from.getAccountId().compareTo(to.getAccountId()) < 0 ? from : to;
        Object second = first == from ? to : from;

        // 给ID较小的账号加锁
        synchronized (first) {
            System.out.println(Thread.currentThread().getName() + " 获得 " + from.getAccountId() + " 锁");
            // 给ID较大的账号加锁
            synchronized (second) {
                System.out.println(Thread.currentThread().getName() + " 获得 " + to.getAccountId() + " 锁");
                if (from.getBalance() >= amount) {
                    from.decrease(amount);
                    to.increase(amount);
                    System.out.println("转账成功：" + from.getAccountId() + " -> " + to.getAccountId() + "，金额：" + amount);
                }
            }
        }
    }

    /*
    public static void main(String[] args) throws InterruptedException {
        Account a = new Account("A", 1000);
        Account b = new Account("B", 1000);

        // 线程1：A向B转账
        Thread t1 = new Thread(() -> {
            transfer(a, b, 200);
        }, "线程1");

        // 线程2：B向A转账
        Thread t2 = new Thread(() -> {
            transfer(b, a, 300);
        }, "线程2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("最终余额：A=" + a.getBalance() + ", B=" + b.getBalance());
    }
     */


    public static void main(String[] args) throws InterruptedException {
        Account a = new Account("A", 10000);
        Account b = new Account("B", 10000);

        // 模拟10个线程，分别执行：A->B、B->A
        List<Thread> tList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread t = new Thread(() -> {
                if (finalI % 2 == 0) {
                    transfer(a, b, 200);
                } else {
                    transfer(b, a, 300);
                }
            }, "线程1");
            tList.add(t);
        }
        tList.forEach(Thread::start);
        tList.forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("最终余额：A=" + a.getBalance() + ", B=" + b.getBalance());
    }


}