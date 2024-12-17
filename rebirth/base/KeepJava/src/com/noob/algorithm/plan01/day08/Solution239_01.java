package com.noob.algorithm.plan01.day08;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 239 滑动窗口最大值
 */
public class Solution239_01 {

    // 构建大顶堆：维护k个元素的滑动窗口的最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;

        // 构建大顶堆(int[]:{数组元素,对应索引位置})
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });

        // 初始化K个元素入堆
        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i], i});
        }

        // 初始化滑动窗口最大值的结果集
        int[] maxRes = new int[len - k + 1];
        maxRes[0] = pq.peek()[0]; // 初始化第一个窗口位置的最大值
        int cur = 1; // 定义最大值结果集填充位置(也是当前滑动窗口左边界位置)

        // 继续遍历剩余的元素，然后滑动窗口
        for (int i = k; i < len; i++) {
            // 元素入堆
            pq.offer(new int[]{nums[i], i});
            // 判断当前最大值元素所在索引是否小于当前边界左侧，说明这个值已经划出了滑动窗口，可以直接弹出元素（while校验）
            while (pq.peek()[1] < cur) {
                pq.poll();
            }
            maxRes[cur++] = pq.peek()[0];// 此时栈顶元素即对应当前滑动窗口的最大值
        }

        // 返回处理的结果集
        return maxRes;
    }

}
