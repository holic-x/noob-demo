package com.noob.base.scene.cache.cacheAvalanche;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * [缓存雪崩]解决方案：设置随机过期时间
 */
public class CacheWithRandomExpiration {
    private Map<String, CacheObject> cache = new ConcurrentHashMap<>();

    public Object get(String key) {
        CacheObject cacheObject = cache.get(key);
        if (cacheObject == null || cacheObject.isExpired()) {
            Object value = queryDatabase(key);
            // Simulate refresh cache with random expiration to avoid snowball effect  
            cache.put(key, new CacheObject(value, System.currentTimeMillis() + getRandomExpirationTime()));
        }
        return cacheObject.getValue();
    }

    private long getRandomExpirationTime() {
        // Random expiration between 5 to 10 seconds  
        return 5000 + new Random().nextInt(5000);
    }

    private Object queryDatabase(String key) {
        // Simulate a database query  
        return "Value from DB for " + key;
    }

    private static class CacheObject {
        private Object value;
        private long expire;

        public CacheObject(Object value, long expire) {
            this.value = value;
            this.expire = expire;
        }

        public boolean isExpired() {
            return System.currentTimeMillis() > expire;
        }

        public Object getValue() {
            return value;
        }
    }
}