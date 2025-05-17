package com.noob.algorithm.daily.plan03.hot100_random.day03.p005;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 🔴 239 滑动窗口最大值 - https://leetcode.cn/problems/sliding-window-maximum/submissions/598655671/
 */
public class Solution239_01 {

    /**
     * 思路分析：
     * 构建大顶堆维护窗口内的最大值，校验索引与当前遍历元素位置
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        // 定义大顶堆(优先按照值的大小排序(从大到小)，其次按照index(从小到大))
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(
                new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return o2[0] == o1[0] ? o1[1] - o2[1] : o2[0] - o1[0]; // 优先val比较(从大到小)，val相等则基于index(从小到大)
                    }
                }
        );

        // 初始化K个元素入堆
        for (int i = 0; i < k; i++) {
            priorityQueue.offer(new int[]{nums[i], i});
        }

        int n = nums.length;
        int[] res = new int[n - k + 1];// 初始化结果集
        int curLeft = 0; // 初始化窗口左边界
        res[curLeft++] = priorityQueue.peek()[0]; // 当前初始化的堆顶元素即为最大值

        // 遍历剩余元素，校验内容
        for (int i = k; i < nums.length; i++) {
            // 新元素入堆
            priorityQueue.offer(new int[]{nums[i], i});

            // 校验当前堆顶元素索引是否在有效的窗口范围[curLeft,curLeft+k-1]，如果不在则弹出
            while (priorityQueue.peek()[1] < curLeft) {
                priorityQueue.poll(); // 弹出不满足窗口范围的最大值
            }
            // 直到直到满足有效窗口范围内的值
            res[curLeft++] = priorityQueue.peek()[0];
        }

        // 返回结果集
        return res;
    }

}
