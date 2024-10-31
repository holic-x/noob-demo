package com.noob.algorithm.common150.q146;

import java.util.LinkedHashMap;

/**
 * 146 LRU缓存
 */
class LRUCache02 {

    // 借助LinkedHashMap存储
    LinkedHashMap<Integer,Integer> map ;

    // 容量
    int capacity ;

    // 初始化
    public LRUCache02(int capacity) {
        map = new LinkedHashMap<>();
        this.capacity = capacity;
    }

    // get 是访问操作，如果元素存在且被访问则需更新它的位置
    public int get(int key) {
        if(map.containsKey(key)){
            int curVal = map.get(key);
            // 元素被访问，需要更新它的位置(先移除后增加)
            map.remove(key);
            map.put(key,curVal);
            return curVal;
        }else{
            return -1;
        }
    }

    public void put(int key, int value) {
        // 校验元素是否存在
        if(map.containsKey(key)){
            // 存在则删除后再增加（确保最近访问的元素放在后面）
            map.remove(key);
            map.put(key,value);
        }else{
            // 元素不存在则校验是否超出阈值，超出阈值则需执行LRU策略腾挪位置，清理掉最近未被访问的第一个元素
            if(map.size()+1>this.capacity){
                // 清理最久未被使用的元素（即map的第一个元素）
                map.remove(map.keySet().iterator().next());
            }
            // 插入新元素
            map.put(key,value);
        }
    }

}

