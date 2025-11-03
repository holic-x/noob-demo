package com.noob.base.runtimeFailures.deadThread;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


/**
 * 构建资源管理者
 */
class Allocator {
    // 单例设计
    private static Allocator allocator = new Allocator();

    private Allocator() {
    }

    public static Allocator getAllocator() {
        return allocator;
    }

    // 内置列表存放资源
    private List<Object> als = new ArrayList<>();

    // 资源申请
    synchronized boolean apply(Object from, Object to) {
        if (als.contains(from) || als.contains(to)) { // 一组资源，但凡其中一个被占用都不允许其他的线程申请
            return false; // 资源申请失败（某个资源处于占用状态）
        } else {
            als.add(from);
            als.add(to);
        }
        return true;
    }

    // 资源归还
    synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);
    }

}


/**
 * 预防死锁优化：破坏占用且等待条件
 */
@Slf4j
public class SafeTransferDemo2 {

    // 构建单例实例
    private static Allocator allocator = Allocator.getAllocator();

    // 转账方法
    public static void transfer(Account from, Account to, int amount) {
        // 1.尝试一次性申请资源，直到成功
        while (!allocator.apply(from, to)) {
            log.info("尝试一次性申请资源：from={},to={}", from.getAccountId(), to.getAccountId());
        }
        log.info("资源申请成功：from={},to={}", from.getAccountId(), to.getAccountId());

        // 2.只有申请资源成功的线程才能处理转账
        try {
            synchronized (from) {
                synchronized (to) {
                    if (from.getBalance() >= amount) {
                        from.decrease(amount);
                        to.increase(amount);
                        System.out.println("转账成功：" + from.getAccountId() + " -> " + to.getAccountId() + "，金额：" + amount);
                    }
                }
            }
        } finally {
            // 不管处理是否成功，最终都要释放资源
            allocator.free(from, to);
        }

        log.info("转账完成：{}剩余账户金额{}，{}剩余账户金额{}", from.getAccountId(), from.getBalance(), to.getAccountId(), to.getBalance());
    }


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
}