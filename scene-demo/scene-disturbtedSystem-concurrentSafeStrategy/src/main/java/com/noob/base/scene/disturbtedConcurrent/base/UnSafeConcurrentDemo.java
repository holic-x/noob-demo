package com.noob.base.scene.disturbtedConcurrent.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 分布式并发场景：【并发不安全】处理demo
 */
public class UnSafeConcurrentDemo {

    private static int stock = 1;

    private static void modDelay() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // throw new RuntimeException(e);
        }
    }

    // 下单操作
    public static void placeOrder(String userName) {
        // 判断库存是否足够
        if (stock > 0) {
            modDelay(); // 模拟处理耗时
            stock--;
            System.out.printf("用户[%s]下单成功%n", userName);
        } else {
            System.out.printf("用户[%s]下单失败，当前库存不足%n", userName);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("抢购活动开始");

        // 模拟用户并发抢购
        List<Thread> tList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread t = new Thread(() -> {
                placeOrder("user-" + finalI);
            });
            tList.add(t);
        }

        // 执行
        for (int i = 0; i < 10; i++) {
            tList.get(i).start();
        }
        for (int i = 0; i < 10; i++) {
            tList.get(i).join();
        }

        System.out.println("抢购活动结束");
    }

}
