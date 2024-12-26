package com.noob.algorithm.daily.codeTop;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 🟡 215 数组中的第K个最大的元素
 */
public class Solution215_01 {
    /**
     * TOPK 问题
     * 思路1：大顶堆
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        // 构建大顶堆
        for (int i = 0; i < nums.length; i++) {
            pq.offer(nums[i]);
        }

        // 弹出前K-1个元素
        for (int i = 0; i < k - 1; i++) {
            pq.poll();
        }

        return pq.poll();
    }
}
