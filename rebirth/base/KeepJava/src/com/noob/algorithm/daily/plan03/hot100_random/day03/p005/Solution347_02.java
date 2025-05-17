package com.noob.algorithm.daily.plan03.hot100_random.day03.p005;

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
     * 思路分析：小顶堆思路（优化空间效率）
     */
    public int[] topKFrequent(int[] nums, int k) {
        // 统计元素出现频率
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 构建小顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return map.get(o1) - map.get(o2);
                    }
                }
        );


        // 初始化k个元素入堆
        int cur = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (cur < k) {
                pq.offer(entry.getKey());
                cur++;
            }
            // 超出k的部分，则校验当前堆顶的最小元素与当前要处理的元素，将最小的值替换出来
            int topCnt = map.get(pq.peek());
            int curCnt = entry.getValue();
            if (topCnt < curCnt) {
                pq.poll(); // 弹出堆顶元素
                pq.offer(entry.getKey()); // 出现频率更多的新元素入堆
            }
        }

        // 定义结果集
        int[] res = new int[k];
        int idx = 0;

        // 最终小顶堆内留存的是出现频率前k的元素，依次弹出
        while (!pq.isEmpty()) {
            res[idx++] = pq.poll();
        }

        // 返回结果集
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        Solution347_02 solution = new Solution347_02();
        solution.topKFrequent(nums, 2);
    }
}
