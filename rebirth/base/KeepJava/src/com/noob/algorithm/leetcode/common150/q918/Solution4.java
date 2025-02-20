package com.noob.algorithm.leetcode.common150.q918;

/**
 * 918 环形子数组的最大和
 */
public class Solution4 {
    public int maxSubarraySumCircular(int[] nums) {
        /**
         * 拆分为两部分：
         * [场景1]：中间段
         * - 思路和单数组求解最大子数组和一致
         * [场景2]：头尾段（最大前缀和+固定后缀和）
         * - 1.[0，i] 的最大前缀和
         * - 2.[j,n] 的固定后缀和
         */
        int len = nums.length;

        // 场景1：求最大子数组和（Kadane 算法优化版本）所需参数
        int pre = nums[0]; // pre 指向以上一位置结尾的最大子数组和
        int max1 = nums[0]; // max1 记录数组整体的最大子数组和（遍历的时候同步更新）

        // 场景2：求头（最大前缀和）+尾（固定后缀和） 针对头段部分求解所需参数
        int[] leftMax = new int[len]; // leftMax[i] 指向当前位置的最大前缀和
        int curSum = 0; // 当前累加和
        leftMax[0] = 0;

        // 遍历数组元素分别进行求解
        for (int i = 1; i < len; i++) {
            // 求最大子数组和
            pre = Math.max(pre + nums[i], nums[i]);
            max1 = Math.max(pre, max1);
            // 求最大前缀和
            curSum = curSum + nums[i - 1]; // sum[i] = sum[i-1] + nums[i-1] => res = res + nums[i-1]（空间优化）
            leftMax[i] = Math.max(leftMax[i - 1], curSum);
        }

        // 2.逆序遍历计算固定后缀和（此处逆序遍历就和上面的正序遍历i保持一致）
        int rightSum = 0;
        int max2 = Integer.MIN_VALUE; // max2 记录场景2中的最大连续环形子数组和
        for (int i = len - 1; i >= 0; i--) {
            rightSum = rightSum + nums[i]; // 固定后缀和
            max2 = Math.max(max2, rightSum + leftMax[i]); // 更新【场景2】中的最大值
        }

        // 最终返回max1、max2两种场景的最大的情况
        return Math.max(max1, max2);
    }
}

