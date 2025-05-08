package com.noob.algorithm.daily.plan03.hot100_random.day03.p005;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 🟡 347 前K个高频元素 - https://leetcode.cn/problems/top-k-frequent-elements/description/
 */
public class Solution347_01 {

    /**
     * 概要：给定整数数组nums和整数k，返回出现频率前k高的元素
     * 思路分析：
     */
    public int[] topKFrequent(int[] nums, int k) {

        // 遍历元素，统计每个元素的出现次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 定义大顶堆，将元素入堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return map.get(o2) - map.get(o1);
                    }
                }
        );

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.offer(entry.getKey());
        }

        // 定义结果集
        int[] res = new int[k];

        // 弹出频率前K高的元素
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll();
        }

        // 返回结果集
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        Solution347_01 solution = new Solution347_01();
        solution.topKFrequent(nums, 2);
    }
}
