package com.noob.base.scene.disturbtedConcurrent.redisLock.redisBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 分布式并发场景：【并发安全】处理demo
 * - 引入Redis分布式锁
 */
@RestController
@RequestMapping("/redis")
public class RedisLockController {

    @Autowired
    private SecSkillService secSkillService;

    @RequestMapping("/placeOrder")
    public void placeOrder() throws InterruptedException {

        System.out.println("抢购活动开始");

        // 模拟用户并发抢购
        List<Thread> tList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread t = new Thread(() -> {
                try {
                    secSkillService.placeOrder("飞天茅台-1", "user-" + finalI);
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
