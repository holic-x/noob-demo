package com.noob.base.scene.disturbtedConcurrent.redisLock.redLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 分布式并发场景：【并发安全】处理demo
 * - 引入Redis分布式锁（基于redisson模式 实现红锁）
 */
@RestController
@RequestMapping("/redissonRedLock")
public class RedissonRedLockController {

    @Autowired
    private SecSkillRedLockService secSkillRedLockService;

    @RequestMapping("/placeOrder")
    public void placeOrder() throws InterruptedException {

        System.out.println("抢购活动开始");

        // 模拟用户并发抢购
        List<Thread> tList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread t = new Thread(() -> {
                try {
                    secSkillRedLockService.placeOrder("user-" + finalI);
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
