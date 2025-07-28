package com.noob.algorithm.daily.plan03.hot100_daily.day03.p005;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 🟡 347 前K个高频元素 - https://leetcode.cn/problems/top-k-frequent-elements/description/
 */
public class Solution347_02 {

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

        // topK大问题：构建小顶堆（优化空间效率），最终每次都将堆顶的min置换出来，最终堆中留存的即为topK（依次从小到大弹出，逆序处理一下即可）
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]; // 优先频率从小到大，其次元素值从小到大
                    }
                }
        );

        // 遍历元素构建小顶堆（k个元素）
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (pq.size() < k) {
                // [0,k)的元素，直接入堆
                pq.offer(new int[]{entry.getKey(), entry.getValue()});
            } else {
                // [k,size)的元素，置换处理
                if (!pq.isEmpty()) {
                    if (pq.peek()[1] < entry.getValue()) {
                        // 有更大的元素出现频率，进行置换
                        pq.poll();
                        pq.offer(new int[]{entry.getKey(), entry.getValue()});
                    }
                }
            }
        }

        // 构建结果集合
        int[] ans = new int[k];
        int pt = 0; // 遍历指针
        while (!pq.isEmpty()) {
            ans[pt++] = pq.poll()[0];
        }

        // 返回结果集
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        Solution347_02 solution = new Solution347_02();
        solution.topKFrequent(nums, 2);
    }
}
