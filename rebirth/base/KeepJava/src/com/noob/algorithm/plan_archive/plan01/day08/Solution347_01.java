package com.noob.algorithm.plan_archive.plan01.day08;

import java.util.*;

/**
 * 347 前K个高频元素
 */
public class Solution347_01 {

    // top K 大问题：统计频率 + 大顶堆前K大
    public int[] topKFrequent(int[] nums, int k) {
        // 构建map存储每个元素的出现频率
        Map<Integer, Integer> map = new HashMap<>();
        for (int cur : nums) {
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }
        // 维护大顶堆(堆存储的元素是map的key值，比较的是value的大小)
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer key1, Integer key2) {
                return map.get(key2) - map.get(key1);
            }
        });
        // 构建大顶堆
        Set<Integer> keySet = map.keySet();
        for (int key : keySet) {
            pq.offer(key);
        }

        int[] res = new int[k];
        // 弹出大顶堆中的k个元素
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll();
        }
        // 返回结果
        return res;
    }
}
