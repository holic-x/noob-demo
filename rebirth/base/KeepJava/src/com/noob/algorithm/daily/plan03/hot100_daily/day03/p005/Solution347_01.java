package com.noob.algorithm.daily.plan03.hot100_daily.day03.p005;

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
     * 思路分析：大顶堆思路
     * - 统计元素出现频率Map<ch,int>
     * - 使用大顶堆或者小顶堆处理topK问题
     */
    public int[] topKFrequent(int[] nums, int k) {
        // 统计元素出现频率
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // topK大问题：构建大顶堆，最终依次从堆顶弹出K个元素（int[]{元素值,元素出现频率}）
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return o1[1] == o2[1] ? o1[0] - o2[0] : o2[1] - o1[1]; // 优先频率从大到小，其次元素值从小到大
                    }
                }
        );

        // 遍历所有元素，构建堆
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.offer(new int[]{entry.getKey(), entry.getValue()});
        }

        // 弹出k个元素构建结果集
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = pq.poll()[0];
        }

        // 返回结果集
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        Solution347_01 solution = new Solution347_01();
        solution.topKFrequent(nums, 2);
    }
}
