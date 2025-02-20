package com.noob.algorithm.solution_archive.leetcode.common150.q215;

import java.util.PriorityQueue;

/**
 * 215 数组中的第K个最大元素
 */
public class Solution1 {
    // 大顶堆方法：数组元素依次入堆，遍历大顶堆元素，依次弹出栈顶元素，得到第K个即可
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        for (int num : nums) {
            heap.add(num);
        }

        // 弹出K-1个元素
        for (int i = 1; i <= k - 1; i++) {
            heap.poll();
        }

        return heap.peek(); // 第K大
    }
}
