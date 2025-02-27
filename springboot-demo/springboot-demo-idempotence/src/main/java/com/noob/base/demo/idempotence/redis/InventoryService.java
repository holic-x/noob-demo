package com.noob.base.demo.idempotence.redis;

import redis.clients.jedis.Jedis;

/**
 * ③ 基于【Redis分布式锁】实现幂等性
 */
public class InventoryService {

    private Jedis jedis = new Jedis("localhost");

    public boolean deductInventory(String requestId, String productId, int quantity) {
        // 获取分布式锁
        String lockKey = "lock:inventory:" + productId;
        boolean locked = jedis.setnx(lockKey, requestId) == 1;
        if (!locked) {
            return false; // 获取锁失败
        }

        // 检查请求是否已处理
        String processedKey = "processed:" + requestId;
        if (jedis.exists(processedKey)) {
            jedis.del(lockKey); // 释放锁
            return true; // 请求已处理
        }

        // 扣减库存
        deductInventoryInDB(productId, quantity);

        // 记录请求标识
        jedis.set(processedKey, "1");
        jedis.del(lockKey); // 释放锁
        return true;
    }

    // 模拟库存扣减
    private void deductInventoryInDB(String productId, int quantity) {
        // ... 库存扣减 ...
    }
}