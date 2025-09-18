package com.noob.base.runtimeFailures.deadThread;

/**
 * 死锁案例：转账场景
 */
public class TransferExample {

    public static void modDelay() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println("处理异常");
        }
    }

    // 转账方法设计
    public static void transfer(Account from, Account to, int amount) {
        // 给转出账户加锁
        synchronized (from) {
            System.out.println(Thread.currentThread().getName() + " 获得 " + from.getAccountId() + " 锁");

            // 模拟网络延迟，增加死锁概率
            modDelay(); // 等价于：Thread.sleep(100); 此处方法内部捕获了异常

            // 给转入账户加锁
            synchronized (to) {
                System.out.println(Thread.currentThread().getName() + " 获得 " + to.getAccountId() + " 锁");

                if (from.getBalance() >= amount) {
                    from.decrease(amount);
                    to.increase(amount);
                    System.out.println("转账成功：" + from.getAccountId() + " -> " + to.getAccountId() + "，金额：" + amount);
                }
            }
        }
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