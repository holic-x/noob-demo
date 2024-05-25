package com.noob.collection;

import java.util.*;

class LRUCache<K,V> extends LinkedHashMap<K,V> {
    private final int capacity;
    public LRUCache(int capacity) {
        // 初始化容量16，负载因子0.75，访问顺序排序
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // return super.removeEldestEntry(eldest);
        return size() > capacity;
    }
}

/**
 * 基于LinkedHashMap模拟LRU缓存机制
 */
public class LRUCacheDemo {
    public static void main(String[] args) {
        LRUCache<Integer,String> cache = new LRUCache<>(5);
        cache.put(1, "JAVA");
        cache.put(2, "PYTHON");
        cache.put(3, "GO");
        cache.put(4, "C++");
        cache.put(5, "C");
        System.out.println("-------初始化缓存内容--------");
        System.out.println(cache);
        System.out.println("-------验证超出容量--------");
        cache.put(6,"C#");
        System.out.println(cache);
        System.out.println("-------验证LRU缓存机制---------");
        cache.get(2);
        System.out.println(cache);
        cache.put(7, "REDIS");
        System.out.println(cache);
    }
}
