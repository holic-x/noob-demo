package com.noob.algorithm.leetcode.common150.q215;

import java.util.PriorityQueue;

/**
 * 215 数组中的第K个最大元素
 */
public class Solution2 {
    /**
     * 小顶堆方法：维护一个K大小的小顶堆
     * 1.先初始化小顶堆（K个元素）
     * 2.随后继续遍历后面的元素，如果元素大于当前的堆顶元素则进行置换（这个遍历过程会逐步将当前小顶堆中最小的元素置换出来，最终小顶堆中存放是K个最大的元素）
     * 3.遍历完成，最终小顶堆中存放是K个最大的元素，则此时堆顶元素就是第K大的元素
     */
    public int findKthLargest(int[] nums, int k) {
        // 初始化小顶堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        // 初始化K个元素入堆
        for(int i=0;i<k;i++){
            heap.add(nums[i]);
        }

        // 继续遍历剩下的元素
        for(int i=k;i<nums.length;i++){
            // 判断当前遍历元素是否大于小顶堆堆顶元素，如果大于则进行置换，确保小顶堆中存放的是目前已遍历的最大的元素集合，逐步将最小值剔出
            int cur = heap.peek();
            if(nums[i]>cur){
                heap.poll(); // 剔出堆顶元素
                heap.add(nums[i]); // 补入新元素
            }
        }

        return heap.peek(); // 第K大
    }
}
