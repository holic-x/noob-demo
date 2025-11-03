package com.noob.base.runtimeFailures.deadThread;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


// 账户类
@Setter
@Getter
class LockAccount {
    private String accountId; // 账户ID
    private int balance; // 余额
    private Lock lock = new ReentrantLock();

    public LockAccount(String accountId, int balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    // 增加余额
    public void increase(int amount) {
        balance += amount;
    }

    // 减少余额
    public void decrease(int amount) {
        balance -= amount;
    }
}

/**
 * 预防死锁优化：破坏不可抢占条件
 */
@Slf4j
public class SafeTransferDemo3 {

    // 转账方法：用Lock替换嵌套synchronized方案
    public static void transfer(LockAccount from, LockAccount to, int amount) {
        // 定义资源from、to的锁
        Lock fromLock = from.getLock();
        Lock toLock = to.getLock();

        // 定义获取锁结果(成功、失败)
        boolean gotFrom = false;
        boolean gotTo = false;

        try {
            // 1.加锁：尝试获取from锁（限制超时等待阈值100ms），如果超时没有拿到则放弃
            gotFrom = fromLock.tryLock(100, TimeUnit.MILLISECONDS);
            if (!gotFrom) {
                log.info("尝试获取【from锁】失败，放弃转账");
                return; // 终止操作，放弃申请资源
            }

            // 2.加锁：尝试获取to锁（限制超时等待阈值100ms），如果超时没有拿到则放弃
            gotTo = toLock.tryLock(100, TimeUnit.MILLISECONDS);
            if (!gotTo) {
                log.info("尝试获取【to锁】失败，放弃转账");
                return; // 终止操作，放弃申请资源
            }

            // 3.两把锁都获取成功，可以执行转账操作
            if (from.getBalance() >= amount) {
                from.decrease(amount);
                to.increase(amount);
                log.info("本次操作转账成功：{}给{}转了{}", from.getAccountId(), to.getAccountId(), amount);
            }

        } catch (InterruptedException e) {
            log.info("线程被中断", e);
            Thread.currentThread().interrupt();
        } finally {
            // 清理资源(如果成功获取了锁，在最终要清理释放锁：遵循后获取、先释放)
            if (gotTo) {
                toLock.unlock();
            }
            if (gotFrom) {
                fromLock.unlock();
            }
        }

    }


    // 转账方法：用Lock替换嵌套synchronized方案，并固定加锁顺序
    public static void transferByOrder(LockAccount from, LockAccount to, int amount) {
        // 先根据账号ID进行排序，明确锁顺序
        Lock firstLock = from.getAccountId().compareTo(to.getAccountId()) < 0 ? from.getLock() : to.getLock(); // 第1把锁：ID较小的账号
        Lock secondLock = from.getAccountId().compareTo(to.getAccountId()) >= 0 ? from.getLock() : to.getLock(); // 第2把锁：ID较大的账号

        // 定义获取锁结果(成功、失败)
        boolean gotFirstLock = false;
        boolean gotSecondLock = false;

        try {
            // 1.加锁：尝试获取firstLock（限制超时等待阈值100ms），如果超时没有拿到则放弃
            gotFirstLock = firstLock.tryLock(100, TimeUnit.MILLISECONDS);
            if (!gotFirstLock) {
                log.info("尝试获取【firstLock】失败，放弃转账");
                return; // 终止操作，放弃申请资源
            }

            // 2.加锁：尝试获取secondLock（限制超时等待阈值100ms），如果超时没有拿到则放弃
            gotSecondLock = secondLock.tryLock(100, TimeUnit.MILLISECONDS);
            if (!gotSecondLock) {
                log.info("尝试获取【secondLock】失败，放弃转账");
                return; // 终止操作，放弃申请资源
            }

            // 3.两把锁都获取成功，可以执行转账操作
            if (from.getBalance() >= amount) {
                from.decrease(amount);
                to.increase(amount);
                log.info("本次操作转账成功：{}给{}转了{}", from.getAccountId(), to.getAccountId(), amount);
            }

        } catch (InterruptedException e) {
            log.info("线程被中断", e);
            Thread.currentThread().interrupt();
        } finally {
            // 清理资源(如果成功获取了锁，在最终要清理释放锁：遵循后获取、先释放)
            if (gotSecondLock) {
                secondLock.unlock();
            }
            if (gotFirstLock) {
                firstLock.unlock();
            }
        }
    }


    // 测试2：Lock 替换 嵌套 synchronized + 固定加锁顺序
    public static void main(String[] args) throws InterruptedException {
        LockAccount a = new LockAccount("A", 1000);
        LockAccount b = new LockAccount("B", 1000);

        // 线程1：A向B转账
        Thread t1 = new Thread(() -> {
            transferByOrder(a, b, 200);
        }, "线程1");

        // 线程2：B向A转账
        Thread t2 = new Thread(() -> {
            transferByOrder(b, a, 300);
        }, "线程2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("最终余额：A=" + a.getBalance() + ", B=" + b.getBalance());
    }

    /*
    // 测试1：Lock 替换 嵌套 synchronized
    public static void main(String[] args) throws InterruptedException {
        LockAccount a = new LockAccount("A", 1000);
        LockAccount b = new LockAccount("B", 1000);

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
}