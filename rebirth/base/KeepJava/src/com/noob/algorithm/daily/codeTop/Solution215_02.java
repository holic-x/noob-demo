package com.noob.algorithm.daily.codeTop;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 🟡 215 数组中的第K个最大的元素
 */
public class Solution215_02 {
    /**
     * TOPK 问题
     * 思路2：维护K个元素的小顶堆
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        // 维护K个元素的小顶堆
        for (int i = 0; i < k; i++) {
            pq.offer(nums[i]);
        }
        // 从第k+1个元素（下标为k）开始，依次将小顶堆中的最小值置换出来
        for (int i = k; i < nums.length; i++) {
            int topVal = pq.peek();
            if (nums[i] > topVal) { // 如果当前遍历的值大于topVal则进行置换
                pq.poll();
                pq.offer(nums[i]);
            }
        }

        // 当前的栈顶元素指向的就是第K大元素
        return pq.poll();
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int[] nums = new int[]{-1, 2, 0};
        Solution215_02 s = new Solution215_02();
        s.findKthLargest(nums, 2);
    }
}
