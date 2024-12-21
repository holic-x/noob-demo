package com.noob.algorithm.daily.plan01.day08;

import java.util.*;

/**
 * 347 前K个高频元素
 */
public class Solution347_02 {

    // top K 大问题：统计频率 + 小顶堆（维护K个元素）（空间优化）
    public int[] topKFrequent(int[] nums, int k) {
        // 构建map存储每个元素的出现频率
        Map<Integer, Integer> map = new HashMap<>();
        for (int cur : nums) {
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }
        // 维护K个元素的小顶堆(堆存储的元素是map的key值，比较的是value的大小)
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer key1, Integer key2) {
                return map.get(key1) - map.get(key2);
            }
        });
        // 构建小顶堆：先初始化k个元素，剩余元素则依次比较堆顶元素和当前遍历元素，不断将堆中最小的数置换出来
        Set<Integer> keySet = map.keySet();
        for (int key : keySet) {
            if (pq.size() < k) {
                pq.offer(key); // 当小顶堆个数不足K则依次添加
            } else {
                if (map.get(pq.peek()) < map.get(key)) { // 当小顶堆个数大于等于K，则依次将小顶堆的频率最小的置换出来
                    pq.poll();
                    pq.offer(key);
                }
            }
        }

        int[] res = new int[k];
        int idx = 0;
        // 弹出小顶堆元素
        while (!pq.isEmpty()) {
            res[idx++] = pq.poll();
        }
        // 返回结果
        return res;
    }
}
