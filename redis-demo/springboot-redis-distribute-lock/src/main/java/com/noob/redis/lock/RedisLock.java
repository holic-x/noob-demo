package com.noob.redis.lock;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.UUID;

/**
 * redis分布式锁
 */
@Slf4j
public class RedisLock {

    // 锁key的前缀
    private final static String prefix_key = "redisLock:";
    // 释放锁的lua脚本
    private final static String unLockScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
    // 执行unLockScript脚本，释放锁成功值
    private final static Long unLockSuccess = 1L;

    // 加锁设置的参数（key值、超时时间、持有锁的时间）
    private LockParam lockParam;
    // 尝试获得锁的截止时间【lockParam.getTryLockTime()+System.currentTimeMillis()】
    private Long tryLockEndTime;
    // redis加锁的key
    private String redisLockKey;
    // redis加锁的value
    private String redisLockValue;
    // redis加锁的成功标示
    private Boolean holdLockSuccess = Boolean.FALSE;

    // jedis实例
    private Jedis jedis;

    // 获取jedis实例
    private Jedis getJedis() {
        return this.jedis;
    }

    // 关闭jedis
    private void closeJedis(Jedis jedis) {
        jedis.close();
        jedis = null;
    }

    public RedisLock(LockParam lockParam) {
        if (lockParam == null) {
            throw new RuntimeException("lockParam is null");
        }
        if (lockParam.getLockKey() == null || lockParam.getLockKey().trim().isEmpty()) {
            throw new RuntimeException("lockParam lockKey is error");
        }
        this.lockParam = lockParam;

        this.tryLockEndTime = lockParam.getTryLockTime() + System.currentTimeMillis();
        this.redisLockKey = prefix_key.concat(lockParam.getLockKey());
        this.redisLockValue = UUID.randomUUID().toString().replaceAll("-", "");

        // todo 自定义获取Jedis实例的实现
        jedis = new Jedis("127.0.0.1", 6379);
    }

    /**
     * 加锁
     *
     * @return 成功返回true，失败返回false
     */
    public boolean lock() {
        while (true) {
            // 判断是否超过了，尝试获取锁的时间
            if (System.currentTimeMillis() > tryLockEndTime) {
                return false;
            }
            // 尝试获取锁
            holdLockSuccess = tryLock();
            if (Boolean.TRUE.equals(holdLockSuccess)) {
                return true;//获取锁成功
            }

            try {
                // 获得锁失败，休眠50毫秒再去尝试获得锁,避免一直请求redis，导致redis cpu飙升
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 执行一次加锁操作：成功返回true 失败返回false
     *
     * @return 成功返回true，失败返回false
     */
    private boolean tryLock() {
        try {
            // todo 指令版本确认
            String result = getJedis().set(redisLockKey, redisLockValue, "NX", "PX", lockParam.getHoldLockTime()); // jedis 2.9.3 版本
            if ("OK".equals(result)) {
                return true;
            }
        } catch (Exception e) {
            log.warn("tryLock failure redisLockKey:{} redisLockValue:{} lockParam:{}", redisLockKey, redisLockValue, lockParam, e);
        }
        return false;
    }

    /**
     * 解锁
     *
     * @return 成功返回true，失败返回false
     */
    public Boolean unlock() {
        Object result = null;
        try {
            // 获得锁成功，才执行lua脚本
            if (Boolean.TRUE.equals(holdLockSuccess)) {
                // 执行Lua脚本
                result = getJedis().eval(unLockScript, Collections.singletonList(redisLockKey), Collections.singletonList(redisLockValue));
                if (unLockSuccess.equals(result)) {// 释放成功
                    return true;
                }
            }
        } catch (Exception e) {
            log.warn("unlock failure redisLockKey:{} redisLockValue:{} lockParam:{} result:{}", redisLockKey, redisLockValue, lockParam, result, e);
        } finally {
            this.closeJedis(jedis);
        }
        return false;
    }
}
