package com.noob.algorithm.solution_archive.dmsxl.leetcode.q239;

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
        for (int i = 0; i < k; i++) {
            queue.offer(new int[]{nums[i], i});
        }

        int[] max = new int[len - k + 1]; // 初始化最大值结果集
        max[0] = queue.peek()[0]; // 此时堆顶元素为当前的最大值
        int cur = 1; // 定义最大值结果集填充位置(也是当前滑动窗口左边界位置)

        // 继续遍历剩余数组元素
        for (int i = k; i < len; i++) {
            // 1.新元素入堆
            queue.offer(new int[]{nums[i], i});
            // 2.如果当前最大值的元素所在索引小于当前边界左侧（说明已经滑出了滑动窗口可以直接去除，即弹出元素）
            while (queue.peek()[1] < cur) { // 当出现大于等于cur边界的索引值，说明该最大值在窗口内
                queue.poll();
            }
            max[cur++] = queue.peek()[0]; // 此时栈顶元素就是对应当前滑动窗口的最大值
        }

        // 返回结果
        return max;
    }
}
