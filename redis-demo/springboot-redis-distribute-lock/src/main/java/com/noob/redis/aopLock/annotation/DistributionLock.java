package com.noob.redis.aopLock.annotation;

import java.lang.annotation.*;

/**
 * 通常和DistributionLockParam 注解配合使用，降低锁粒度
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DistributionLock {

    /**
     * 分布式锁key
     */
    String key();

    /**
     * 尝试获得锁的时间（单位：毫秒），默认值：5000毫秒
     *
     * @return 锁key过期时间
     */
    long tryLockTime() default 5000;

    /**
     * 尝试获得锁后，持有锁的时间（单位：毫秒），默认值：60000毫秒
     *
     * @return
     */
    long holdLockTime() default 60000;

    /**
     * 分布式锁key 的分隔符（默认 :）
     */
    String delimiter() default ":";

}
