package com.noob.algorithm.leetcode.hot100.q239;

/**
 * 239 滑动窗口最大值
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回 滑动窗口中的最大值
 */
public class Solution1 {

    // 暴力法：依次滑动窗口，计算窗口内元素之和
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 定义滑动窗口最大值
        int[] max = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length - k + 1; i++) {
            max[i] = Integer.MIN_VALUE;
            // 依次遍历，更新当前滑动窗口最大值
            for (int j = i; j < i + k; j++) {
                max[i] = Math.max(max[i], nums[j]);
            }
        }

        return max;
    }

}
