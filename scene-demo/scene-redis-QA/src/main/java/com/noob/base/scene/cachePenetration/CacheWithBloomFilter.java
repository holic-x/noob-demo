package com.noob.base.scene.cachePenetration;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * [缓存穿透]解决方案：布隆过滤器
 */
public class CacheWithBloomFilter {
    private Set<String> bloomFilter = new HashSet<>(); // Simplified bloom filter
    private Map<String, Object> cache = new ConcurrentHashMap<>();

    public Object get(String key) {
        if (!bloomFilter.contains(key)) {
            return null; // Filter out the request  
        }

        Object value = cache.get(key);
        if (value == null) {
            // Simulate DB call  
            value = queryDatabase(key);
            if (value != null) {
                cache.put(key, value);
            } else {
                // Cache empty with a short TTL  
                cache.put(key, null); // or cache a dummy object  
            }
        }
        return value;
    }

    private Object queryDatabase(String key) {
        // Simulate a database query  
        return null; // Simulate nonexistent data  
    }
}