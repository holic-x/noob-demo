package com.noob.algorithm.daily.codeTop;

import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 🟡 146 LRU 缓存机制  todo (另一种解法思路O（1）)
 */
public class Solution146_01 {


}


/**
 * LRUCache：最近最少使用
 * 可以基于双向队列概念辅助处理：（此处用LinkedHashMap构建有序的k-v对）
 * ① put：访问（新增）：
 * - 如果元素不存在，直接追加尾部（追加的过程中需校验缓存是否超出阈值，如果超出则需弹出队首元素）
 * - 如果元素存在，删除元素并追加到尾部（追加的过程中需校验缓存是否超出阈值，如果超出则需弹出队首元素）
 * ② get：访问（获取）
 * - 如果元素不存在，不执行操作
 * - 如果元素存在，删除元素并追加到尾部（相当于更新元素的排序，将元素重新排到队尾）
 * ③ 初始化函数：根据限定容量大小控制存储集合
 */
class LRUCache {

    public int capacity;
    public LinkedHashMap<Integer, Integer> cache;

    LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>();
    }

    int get(int key) {
        int val = -1;
        if (cache.containsKey(key)) {
            val = cache.get(key);
            cache.remove(key);
            // 此处调用put方法处理
            put(key, val);
        }
        return val;
    }

    void put(int key, int value) {
        // 如果已存在key则移除后添加
        if (cache.containsKey(key)) {
            cache.remove(key);
            cache.put(key, value);
        } else {
            // 新增新的key，校验容量
            if (cache.size() >= capacity) {
                // 移除队首元素随后加入新缓存
                int firstKey = cache.keySet().iterator().next();
                cache.remove(firstKey);
            }
            cache.put(key, value);
        }
    }

}
