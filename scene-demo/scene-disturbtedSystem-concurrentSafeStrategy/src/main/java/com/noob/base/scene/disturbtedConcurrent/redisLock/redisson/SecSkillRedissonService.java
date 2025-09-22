package com.noob.base.scene.disturbtedConcurrent.redisLock.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 秒杀服务
@Service
public class SecSkillRedissonService {

    private static int stock = 1;

    // 锁前缀
    public static final String LOCK_KEY = "lock::productId";

    @Autowired
    private Redisson redisson;

    private static void modDelay() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // throw new RuntimeException(e);
        }
    }

    // 下单操作
    public void placeOrder(String userName) throws InterruptedException {

        // 1. 定义锁Key（用商品ID，确保同一商品只有一把锁）
        // String lockKey = LOCK_KEY + goodsId; 此处模拟场景一个商品对应一把锁，实际业务场景应该根据实际商品来区分
        RLock rLock = redisson.getLock(LOCK_KEY);

        // 2.加锁
        rLock.lock();
        try {
            // 3. 抢到锁，执行核心下单逻辑（查库存、扣库存、创建订单）
            // 判断库存是否足够
            if (stock > 0) {
                modDelay(); // 模拟处理耗时
                stock--;
                System.out.printf("用户[%s]下单成功%n", userName);
            } else {
                System.out.printf("用户[%s]下单失败，当前库存不足%n", userName);
            }

        } finally {
            // 4. 无论成功失败，最终都要释放锁（避免死锁）
            rLock.unlock();
        }
    }

}
