package com.noob.algorithm.solution_archive.dmsxl.leetcode.q239;

/**
 * 239 滑动窗口的最大值
 */
public class Solution1 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        // 1.定义数组存储滑动窗口的最大值
        int[] maxArr = new int[len - k + 1]; // maxArr[i] 表示以i为起点的滑动窗口的最大值
        // 2.暴力循环（确定窗口起点和终点，获取滑动窗口的最大值）
        for (int i = 0; i < len - k + 1; i++) { // i 的临界取值受限于滑动窗口大小
            // 统计区间的最大值
            int curMax = Integer.MIN_VALUE;
            for (int left = i; left < i + k; left++) {
                curMax = Math.max(curMax, nums[left]); // 更新最大值
            }
            maxArr[i] = curMax; // 填充数组
        }
        // 返回结果集
        return maxArr;
    }
}
