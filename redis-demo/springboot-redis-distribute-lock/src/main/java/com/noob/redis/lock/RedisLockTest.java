package com.noob.redis.lock;

import lombok.extern.slf4j.Slf4j;

/**
 * Redis 分布式锁测试 原测试样例
 */
@Slf4j
public class RedisLockTest {
    static String lockKey = "666";
    public static void main(String[] args) throws InterruptedException {
        log.info("下面测试两个线程同时，抢占锁的结果");
        Thread thread1 = new Thread(()->{
            testRedisLock();
        });
        thread1.setName("我是线程1");
        Thread thread2 = new Thread(()->{
            testRedisLock();
        });
        thread2.setName("我是线程2");

        //同时启动线程
        thread1.start();
        thread2.start();

        Thread.sleep(1000*20);
        log.info("-----------------我是一条分割线----------------");
        log.info("");
        log.info("");
        log.info("");


        log.info("下面是测试  一个线程获取锁成功后，由于业务执行时间超过了设置持有锁的时间，是否会把其他线程持有的锁给释放掉");
        Thread thread3 = new Thread(()->{
            testRedisLock2();
        });
        thread3.setName("我是线程3");
        thread3.start();

        Thread.sleep(1000*1);//暂停一秒是为了让线程3获的到锁
        Thread thread4 = new Thread(()->{
            testRedisLock();
        });
        thread4.setName("我是线程4");
        thread4.start();
    }

    public static void testRedisLock(){
        LockParam lockParam = new LockParam(lockKey);
        lockParam.setTryLockTime(2000L);//2秒时间尝试获得锁
        lockParam.setHoldLockTime(1000*10L);//获得锁成功后持有锁10秒时间
        RedisLock redisLock = new RedisLock(lockParam);
        try {
            Boolean lockFlag = redisLock.lock();
            log.info("加锁结果：{}",lockFlag);
            if(lockFlag){
                try {
                    //20秒模拟处理业务代码时间
                    Thread.sleep(1000*5L);
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


    public static void testRedisLock2(){
        LockParam lockParam = new LockParam(lockKey);
        lockParam.setTryLockTime(1000*2L);//2秒时间尝试获得锁
        lockParam.setHoldLockTime(1000*2L);//获得锁成功后持有锁2秒时间
        RedisLock redisLock = new RedisLock(lockParam);
        try {
            Boolean lockFlag = redisLock.lock();
            log.info("加锁结果：{}",lockFlag);
            if(lockFlag){
                try {
                    //10秒模拟处理业务代码时间
                    Thread.sleep(1000*10L);
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


