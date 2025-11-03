package com.noob.base.runtimeFailures.deadThread;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;


/**
 * 预防死锁优化：破坏不可抢占条件
 */
@Slf4j
public class SafeTransferDemo5 {

    public static void modDelay() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println("处理异常");
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
                modDelay(); // 模拟耗时验证
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

        // 模拟10个线程，分别执行：A->B、B->A
        List<Thread> tList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread t = new Thread(() -> {
                if (finalI % 2 == 0) {
                    transferByOrder(a, b, 200);
                } else {
                    transferByOrder(b, a, 300);
                }
            }, "线程" + i);
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