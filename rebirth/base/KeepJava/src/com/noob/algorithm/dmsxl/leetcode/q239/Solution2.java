package com.noob.algorithm.dmsxl.leetcode.q239;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 239 堆（优先队列）
 */
public class Solution2 {


    public int[] maxSlidingWindow(int[] nums, int k) {
        // 构建大顶堆（维护元素和对应索引位置）
        int len = nums.length;
        // 大根堆维护：{数组元素值,索引位置}
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] item1, int[] item2) {
                // 数据元素不相等时优先按照元素大小排序，数据元素相等时优先按照索引大小排序
                return item1[0] != item2[0] ? item2[0] - item1[0] : item2[1] - item2[1];
            }
        });

        // 封装大根堆元素(前K个元素进入)
        for(int i=0;i<k;i++){
            queue.offer(new int[]{nums[i],i});
        }

        // 初始化最大值结果集
        int[] max = new int[len-k+1];
        max[0] = queue.peek()[0]; // 此时堆顶元素为当前的最大值

        // 继续遍历剩余数组元素
        for(int i=k;i<len;i++){
            // 1.新元素入堆
            queue.offer(new int[]{nums[i],i});
            //
            while (queue.peek()[1] <= i - k) {
                queue.poll();
            }
            max[i - k + 1] = queue.peek()[0];
        }

        // 返回结果
        return max;
    }
}
