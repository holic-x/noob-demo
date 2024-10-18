package com.noob.algorithm.common150.q380;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * 380 O(1) 时间插入、删除和获取随机元素
 * 思路：借助数组(可变长数组)+哈希表结合使用
 * 哈希表：key 存储实际的元素值，value 存储该元素在可变长数组中对应的下标位置，可变长数组存储相应的元素
 */
class RandomizedSet {

    // 分别定义哈希表、可变长数组、Random对象
    public HashMap<Integer, Integer> map;
    public List<Integer> list;
    public Random random;

    /**
     * 初始化函数(为了简化操作关注核心设计思路，使用现成的数据结构)
     */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }

    /**
     * 插入操作
     * 需插入指定元素值，首先需判断元素是否存在，不存在则插入并返回true，存在则返回false
     */
    public boolean insert(int val) {
        if(map.containsKey(val)) {
            // 元素存在，返回false
            return false;
        }
        // 元素不存在，则需插入元素到集合中（分别插入到HashMap、List）
        list.add(val);
        map.put(val, list.indexOf(val));
        return true;
    }

    /**
     * 删除操作
     * 需分别删除数组和哈希表中的数据，首先需判断元素是否存在，存在则移除并返回true，不存在则返回false
     */
    public boolean remove(int val) {
        // 元素不存在，返回false
        if(!map.containsKey(val)) {
            return false;
        }
        // 元素存在，移除元素并返回true
        int idx = map.get(val);
        map.remove(val);
        list.remove(idx); // 根据index索引移除元素
        return true;
    }
    
    public int getRandom() {
        // 生成随机数，并返回
        return list.get(random.nextInt(list.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */