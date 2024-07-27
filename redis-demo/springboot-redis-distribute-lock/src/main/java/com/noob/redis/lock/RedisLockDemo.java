package com.noob.redis.lock;

import lombok.extern.slf4j.Slf4j;

/**
 * Redis 实现分布式锁 使用案例
 */
@Slf4j
public class RedisLockDemo {
    // 设置锁键 lockKey
    static String lockKey = "666";
    public static void main(String[] args) throws InterruptedException {
        // 测试1：验证分布式锁的互斥性、安全性
        log.info("测试：测试两个线程同时抢占锁的结果");
        Thread thread1 = new Thread(()->{
            // 尝试获得锁时间2秒,获得锁成功后持有锁时间10秒,模拟业务代码执行时间5秒
            testRedisLock(2000L,1000*10L,1000*5L);
        });
        thread1.setName("线程1");
        Thread thread2 = new Thread(()->{
            // 尝试获得锁时间2秒,获得锁成功后持有锁时间10秒,模拟业务代码执行时间5秒
            testRedisLock(2000L,1000*10L,1000*5L);
        });
        thread2.setName("线程2");

        // 同时启动线程
        thread1.start();
        thread2.start();

        // 沉睡一段时间，确保上述所有业务执行完成
        Thread.sleep(1000*20);
        log.info("");
        log.info("---------------------------------分割线--------------------------------");
        log.info("");

        // 测试2：验证分布式锁的对称性（谁申请谁释放）
        log.info("测试：验证一个线程获取锁成功后，由于业务执行时间超过了设置持有锁的时间，是否会把其他线程持有的锁给释放掉");
        Thread thread3 = new Thread(()->{
            // 业务执行时间超过锁的持有时间，业务执行过程中锁过期
            testRedisLock(1000*2L,1000*2L,1000*10L);
        });
        thread3.setName("线程3");
        thread3.start();

        Thread.sleep(1000*1);// 多暂停一秒是为了让线程3获取到锁
        Thread thread4 = new Thread(()->{
            // 线程4
            testRedisLock(2000L,1000*20L,1000*15L);
        });
        thread4.setName("线程4");
        // 线程4启动，此时线程3持有的锁被释放，线程4可以正常获取锁
        thread4.start();
    }

    /**
     * 测试获取Redis分布式锁
     */
    public static void testRedisLock(Long tryLockTime,Long holdLockTime,Long businessTime){
        LockParam lockParam = new LockParam(lockKey);
        lockParam.setTryLockTime(tryLockTime);// 尝试获得锁时间设定
        lockParam.setHoldLockTime(holdLockTime);// 获得锁成功后持有锁时间设定
        RedisLock redisLock = new RedisLock(lockParam);
        try {
            Boolean lockFlag = redisLock.lock();
            log.info("加锁结果：{}",lockFlag);
            if(lockFlag){
                try {
                    // 模拟处理业务代码时间
                    Thread.sleep(businessTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e) {
            log.info("testRedisLock e---->",e);
        }finally {
            boolean unlockResp = redisLock.unlock();
            log.info("释放锁结果:{}",unlockResp);
        }
    }
}
