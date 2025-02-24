package com.noob.algorithm.solution_archive.dmsxl.leetcode.q347;

import java.util.*;

/**
 * 347 前K个高频元素
 */
public class Solution1 {

    /**
     * 暴力法：统计每个元素出现的频率（次数），然后进行排序，返回前K的元素
     */
    public int[] topKFrequent(int[] nums, int k) {

        // 1.统计每个元素出现的频率（次数）
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 2.基于频率进行排序（或者借助堆来构建前K）
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            // 大顶堆存储的是数组元素，排序规则则需根据数组元素获取到频率进行比较
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o2) - map.get(o1); // 根据元素对应的出现频次进行比较构建
            }
        });
        Iterator<Integer> keySetIterator = map.keySet().iterator();
        while (keySetIterator.hasNext()) {
            queue.offer(keySetIterator.next());
        }

        // 3.遍历大顶堆K（K个元素）
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll();
        }

        // 返回结果
        return res;
    }
}
