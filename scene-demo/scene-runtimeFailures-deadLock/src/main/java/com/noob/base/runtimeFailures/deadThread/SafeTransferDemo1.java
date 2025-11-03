package com.noob.base.runtimeFailures.deadThread;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

// 账户类
@Setter
@Getter
class SafeAccount {
    private String accountId; // 账户ID
    private AtomicInteger balance; // 余额 (原子类保证线程安全)

    public SafeAccount(String accountId, AtomicInteger balance) {
        this.accountId = accountId;
        this.balance = balance;
    }
}

/**
 * 预防死锁优化：破坏互斥条件
 */
@Slf4j
public class SafeTransferDemo1 {

    // 转账方法设计：无锁化设计
    public static void transfer(SafeAccount from, SafeAccount to, int amount) {
        if (from.getBalance().get() >= amount) {
            from.getBalance().addAndGet(-amount); // 减少金额
            to.getBalance().addAndGet(amount); // 增加金额
            System.out.println("转账成功：" + from.getAccountId() + " -> " + to.getAccountId() + "，金额：" + amount);
        }
        log.info("转账完成：{}剩余账户金额{}，{}剩余账户金额{}", from.getAccountId(), from.getBalance().get(), to.getAccountId(), to.getBalance().get());
    }

    public static void main(String[] args) throws InterruptedException {
        SafeAccount a = new SafeAccount("A", new AtomicInteger(1000));
        SafeAccount b = new SafeAccount("B", new AtomicInteger(1000));

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