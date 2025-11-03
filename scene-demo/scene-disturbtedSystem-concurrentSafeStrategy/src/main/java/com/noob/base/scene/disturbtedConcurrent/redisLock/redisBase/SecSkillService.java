package com.noob.base.scene.disturbtedConcurrent.redisLock.redisBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 秒杀服务
@Service
public class SecSkillService {

    private static int stock = 1;

    @Autowired
    private RedisDistributedLock redisDistributedLock;

    private static void modDelay() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // throw new RuntimeException(e);
        }
    }

    // 下单操作
    public void placeOrder(String goodsId, String userName) throws InterruptedException {

        // 1. 定义锁Key（用商品ID，确保同一商品只有一把锁）
        String lockKey = "goods:" + goodsId;

        try {
            // 2. 尝试获取锁，最多等待1秒（根据业务调整）
            boolean lockSuccess = redisDistributedLock.tryLock(lockKey, 1000);
            if (!lockSuccess) {
                System.out.printf("用户[%s]获取锁失败，退出竞争\n", userName); // 未抢到锁，直接返回
                return;
            }

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
            redisDistributedLock.unlock(lockKey);
        }
    }

}
