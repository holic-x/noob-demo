package com.noob.redis.aopLock.annotation;

import java.lang.annotation.*;

/**
 * 分布式锁参数：用于给DistributionLock用来控制锁粒度
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DistributionLockParam {
}
