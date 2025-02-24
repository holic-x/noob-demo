package com.noob.algorithm.solution_archive.leetcode.common150.q380;

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
     * 插入操作：Map<key,对应其在数组的下标> list(存储元素)
     * 需插入指定元素值，首先需判断元素是否存在，不存在则插入并返回true，存在则返回false
     */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            // 元素存在，返回false
            return false;
        }
        // 元素不存在，则需插入元素到集合中（分别插入到HashMap、List）
        /**
         * O(1) 思路：由于插入操作每次都是尾部插入，因此新元素的索引就是原数组的大小（下标从0开始计算）
         * 因此此处不需要先插入数组，然后通过indexOf获取索引
         * 而是直接根据数组长度来处理，以此达到O(1)计算的目的
         */
        int curLen = list.size();
        map.put(val, curLen);
        list.add(val);
        return true;
    }

    /**
     * 删除操作
     * 需分别删除数组和哈希表中的数据，首先需判断元素是否存在，存在则移除并返回true，不存在则返回false
     */
    public boolean remove(int val) {
        // 元素不存在，返回false
        if (!map.containsKey(val)) {
            return false;
        }
        // 元素存在，移除元素并返回true
        /**
         * O(1)思路：将待删除的值与最后一个元素进行交换，以确保删除操作可以直接删除更新后数组的最后一个元素，以此达到O(1)目的
         * 交换的过程需要注意两个集合操作都需要交换
         */
        int idx = map.get(val);
        // 交换当前待删除元素和最后一个元素
        int lastVal = list.get(list.size() - 1);
        map.put(lastVal, idx); // map 操作：将最后一个元素移动到当前待删除位置
        list.set(idx, lastVal);// 可变长数组操作：将最后一个元素移动到当前待删除位置
        // 执行删除操作（此处交换后删除的是最后一个元素）
        map.remove(val);// map 操作：删除交换后的最后一个元素（即目标val）
        list.remove(list.size() - 1); // 可变长数组 操作：删除交换后的最后一个元素（即目标val）
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