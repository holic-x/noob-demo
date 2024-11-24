package com.noob.algorithm.dmsxl.leetcode.q1005;

import java.util.PriorityQueue;

/**
 * 1005 K次取反后最大化的数组和
 */
public class Solution2 {

    // 最小堆
    public int largestSumAfterKNegations(int[] nums, int k) {

        // 构建最小堆
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);
        }

        // 取出堆顶元素(每次都拿最小的次数进行取反)
        while (k > 0) {
            int cur = queue.poll();
            cur *= -1;
            queue.offer(cur);
            // 取反完成
            k--;
        }

        // 依次弹出堆元素累加和
        int curMaxSum = 0; // 记录当前的最大数组和
        while (!queue.isEmpty()) {
            curMaxSum += queue.poll();
        }

        return curMaxSum;
    }

}
