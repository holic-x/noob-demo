package com.noob.base.scene.cache.cacheStampede;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * [缓存击穿]解决方案：互斥锁（确保只有一个线程可以执行更新缓存操作）
 */
public class CacheService {
    private volatile Map<String, Object> cache = new ConcurrentHashMap<>();
    private ReentrantLock lock = new ReentrantLock();

    public Object get(String key) {
        Object value = cache.get(key);
        if (value == null) {
            try {
                lock.lock();
                // Double check to ensure value is still not in cache
                value = cache.get(key);
                if (value == null) {
                    // Simulate DB call
                    value = queryDatabase(key);
                    // Update cache
                    cache.put(key, value);
                }
            } finally {
                lock.unlock();
            }
        }
        return value;
    }

    private Object queryDatabase(String key) {
        // Simulate a database query
        return "Value from DB for " + key;
    }
}