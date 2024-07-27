package com.noob.redis.lock;

import lombok.extern.slf4j.Slf4j;

/**
 * 分布式锁的可重入测试
 * 17:28:39.081 [main] DEBUG reactor.util.Loggers - Using Slf4j logging framework
 * 17:28:39.084 [main] DEBUG reactor.core.publisher.Hooks - Enabling stacktrace debugging via onOperatorDebug
 * 17:28:39.190 [main] INFO com.noob.redis.lock.LockReentrantTest - --------------start---------------
 * 17:28:39.199 [main] INFO com.noob.redis.lock.LockReentrantTest - --------lock()执行后，lock.isLocked():true
 * 17:28:39.202 [main] INFO com.noob.redis.lock.LockReentrantTest - --------递归1次--------
 * 17:28:42.160 [main] INFO com.noob.redis.lock.LockReentrantTest - --------lock()执行后，lock.isLocked():false
 * 17:28:42.160 [main] INFO com.noob.redis.lock.LockReentrantTest - --------递归2次--------
 * 17:28:42.161 [main] INFO com.noob.redis.lock.LockReentrantTest - --------lock()执行后，lock.isLocked():false
 * 17:28:42.161 [main] INFO com.noob.redis.lock.LockReentrantTest - --------递归3次--------
 * 17:28:42.166 [main] INFO com.noob.redis.lock.LockReentrantTest - --------unlock()执行后，lock.isLocked():false
 * 17:28:42.166 [main] INFO com.noob.redis.lock.LockReentrantTest - --------unlock()执行后，lock.isLocked():false
 * 17:28:42.166 [main] INFO com.noob.redis.lock.LockReentrantTest - --------unlock()执行后，lock.isLocked():false
 * 17:28:42.166 [main] INFO com.noob.redis.lock.LockReentrantTest - 执行完doSomething方法 是否还持有锁：false
 * 17:28:42.166 [main] INFO com.noob.redis.lock.LockReentrantTest - --------------end---------------
 */
@Slf4j
public class LockReentrantTest {
    // 自定义的分布式锁
    public static RedisLock redisLock;

    // 锁配置
    private static String lockKey = "666";
    private static Long tryLockTime = 1000*3L;
    private static Long holdLockTime = 1000*20L;

    static {
        // 初始化分布式锁
        LockParam lockParam = new LockParam(lockKey);
        lockParam.setTryLockTime(tryLockTime);// 尝试获得锁时间设定
        lockParam.setHoldLockTime(holdLockTime);// 获得锁成功后持有锁时间设定
        redisLock = new RedisLock(lockParam);
    }

    /**
     * 模拟业务操作
     * @param n
     */
    public void doSomething(int n) {
        try {
            // 进入递归第一件事：加锁
            redisLock.lock();
            log.info("--------lock()执行后，lock.isLocked():{}", redisLock.getHoldLockSuccess());
            log.info("--------递归{}次--------", n);
            if (n <= 2) {
                this.doSomething(++n);
            } else {
                return;
            }
        } finally {
            redisLock.unlock();
            log.info("--------unlock()执行后，lock.isLocked():{}", redisLock.getHoldLockSuccess());
        }
    }

    /**
     * 测试自定义的分布式锁是否可重入
     */
    public static void testLockIsReentrant() {
        log.info("--------------start---------------");
        LockReentrantTest lockReentrantTest = new LockReentrantTest();
        lockReentrantTest.doSomething(1);
        log.info("执行完doSomething方法 是否还持有锁：{}", LockReentrantTest.redisLock.getHoldLockSuccess());
        log.info("--------------end---------------");
    }

    public static void main(String[] args) {
        testLockIsReentrant();
    }
}
