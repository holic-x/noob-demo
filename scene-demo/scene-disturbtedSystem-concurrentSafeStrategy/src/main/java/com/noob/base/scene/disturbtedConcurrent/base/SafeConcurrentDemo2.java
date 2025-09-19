package com.noob.base.scene.disturbtedConcurrent.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 分布式并发场景：【并发安全】处理demo
 */
public class SafeConcurrentDemo2 {

    private static int stock = 3;

    private static ReentrantLock lock = new ReentrantLock();

    private static void modDelay() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // throw new RuntimeException(e);
        }
    }

    // 下单操作: 锁处理(灵活Lock机制)
    public static void placeOrder(String userName) throws InterruptedException {
        // 1.获取锁（可设定超时设定）
        // lock.lock();
        boolean gotLock = lock.tryLock(300, TimeUnit.MILLISECONDS); // 设定超时时间内没有等到锁就放弃

        // 2.执行业务逻辑操作
        if (gotLock) {
            try {
                // 判断库存是否足够
                if (stock > 0) {
                    modDelay(); // 模拟处理耗时
                    stock--;
                    System.out.printf("用户[%s]下单成功%n", userName);
                } else {
                    System.out.printf("用户[%s]下单失败，当前库存不足%n", userName);
                }
            } catch (Exception e) {
                System.out.println("业务执行异常");
            } finally {
                // 无论业务执行情况如何，释放锁
                lock.unlock();
            }
        } else {
            System.out.printf("用户[%s]获取锁超时，退出竞争\n", userName);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("抢购活动开始");

        // 模拟用户并发抢购
        List<Thread> tList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread t = new Thread(() -> {
                try {
                    placeOrder("user-" + finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            tList.add(t);
        }

        // 执行
        for (int i = 0; i < tList.size(); i++) {
            tList.get(i).start();
        }
        for (int i = 0; i < tList.size(); i++) {
            tList.get(i).join();
        }

        System.out.println("抢购活动结束");
    }

}
