package com.noob.redis.redisson;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;


@Slf4j
public class ReentrantLockDemo {
    // 锁
    public static RLock lock;

    static {
        // Redisson需要的配置
        Config config = new Config();
        String node = "127.0.0.1:6379";//redis地址
        node = node.startsWith("redis://") ? node : "redis://" + node;
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(node)
                .setTimeout(3000)//超时时间
                .setConnectionPoolSize(10)
                .setConnectionMinimumIdleSize(10);
        // serverConfig.setPassword("123456");//设置redis密码
        // 创建RedissonClient客户端实例
        RedissonClient redissonClient = Redisson.create(config);
        // 创建redisson的分布式锁
        RLock rLock = redissonClient.getLock("666");
        lock = rLock;
    }

    /**
     * 模拟业务操作
     * @param n
     */
    public void doSomething(int n) {
        try {
            // 进入递归第一件事：加锁
            lock.lock();
            log.info("--------lock()执行后，getState()的值：{} lock.isLocked():{}", lock.getHoldCount(), lock.isLocked());
            log.info("--------递归{}次--------", n);
            if (n <= 2) {
                this.doSomething(++n);
            } else {
                return;
            }
        } finally {
            lock.unlock();
            log.info("--------unlock()执行后，getState()的值：{} lock.isLocked():{}", lock.getHoldCount(), lock.isLocked());
        }
    }

    public static void testRedissonLock() {
        log.info("--------------start---------------");
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        reentrantLockDemo.doSomething(1);
        log.info("执行完doSomething方法 是否还持有锁：{}", ReentrantLockDemo.lock.isLocked());
        log.info("--------------end---------------");
    }

    public static void main(String[] args) {
        testRedissonLock();
    }
}
