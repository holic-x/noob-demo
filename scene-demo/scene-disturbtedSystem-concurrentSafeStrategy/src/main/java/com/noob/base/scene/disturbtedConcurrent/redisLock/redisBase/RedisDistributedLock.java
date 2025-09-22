package com.noob.base.scene.disturbtedConcurrent.redisLock.redisBase;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * 秒杀场景专用 Redis 分布式锁（解决超卖问题）
 */
@Component
public class RedisDistributedLock {

    // Redis 操作模板（Spring 项目可直接注入）
    private final RedisTemplate<String, Object> redisTemplate;

    // 锁的前缀（避免与其他 Redis 键冲突）
    private static final String LOCK_PREFIX = "seckill:lock:";

    // 锁的默认过期时间（5秒，防止死锁）
    private static final long DEFAULT_LOCK_EXPIRE = 5000;

    // 锁的默认重试间隔（100毫秒，避免频繁请求 Redis）
    private static final long DEFAULT_RETRY_INTERVAL = 100;

    public RedisDistributedLock(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    /**
     * 尝试获取分布式锁
     *
     * @param lockKey 锁的Key（如商品ID，确保同一商品用同一把锁）
     * @param timeout 最大等待时间（毫秒，超过则放弃）
     * @return true=获取成功，false=获取失败
     */
    public boolean tryLock(String lockKey, long timeout) {
        // 拼接完整的锁Key
        String fullLockKey = LOCK_PREFIX + lockKey;
        // 生成唯一的锁值（用线程ID，释放锁时验证，防止误删他人的锁）
        String lockValue = Thread.currentThread().getId() + ":" + System.currentTimeMillis();
        // 开始时间（用于计算是否超时）
        long startTime = System.currentTimeMillis();

        try {
            while (true) {
                // 1. 原子操作：SET NX EX（不存在则设置，同时加过期时间）
                Boolean lockSuccess = redisTemplate.opsForValue()
                        .setIfAbsent(fullLockKey, lockValue, DEFAULT_LOCK_EXPIRE, TimeUnit.MILLISECONDS);

                // 2. 成功获取锁，直接返回
                if (Boolean.TRUE.equals(lockSuccess)) {
                    return true;
                }

                // 3. 未获取到锁，判断是否已超时，超时则放弃
                if (System.currentTimeMillis() - startTime >= timeout) {
                    return false;
                }

                // 4. 未超时，短暂休眠后重试（避免自旋消耗CPU）
                Thread.sleep(DEFAULT_RETRY_INTERVAL);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 恢复中断状态
            return false;
        }
    }

    /**
     * 释放分布式锁（必须原子操作，防止误删）
     *
     * @param lockKey 锁的Key
     */
    public void unlock(String lockKey) {
        String fullLockKey = LOCK_PREFIX + lockKey;
        String currentLockValue = Thread.currentThread().getId() + ":"; // 匹配当前线程的锁值前缀

        // Redis Lua 脚本：先判断锁值是否属于当前线程，是则删除（原子操作）
        String luaScript = "if string.find(redis.call('get', KEYS[1]), ARGV[1]) then " +
                "return redis.call('del', KEYS[1]) " +
                "else " +
                "return 0 " +
                "end";

        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(luaScript, Long.class);
        // 执行脚本：KEYS[1]是锁Key，ARGV[1]是当前线程的锁值前缀
        redisTemplate.execute(redisScript, Collections.singletonList(fullLockKey), currentLockValue);
    }
}