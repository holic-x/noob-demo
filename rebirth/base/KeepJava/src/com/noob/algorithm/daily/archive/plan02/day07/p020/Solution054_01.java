package com.noob.algorithm.daily.archive.plan02.day07.p020;

/**
 * 🟡 053 最大子数组和 - https://leetcode.cn/problems/maximum-subarray/description/
 */
public class Solution054_01 {

    /**
     * 思路分析：最大子数组和（具有最大和的连续子数组）
     * 贪心思路：避免负数去加入一个元素
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        // n > 1的情况讨论
        int sum = 0;
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            // 判断加上该元素后sum是否会变成负数，如果变成负数则后面的元素没必要拼在其前面，只需要重新自成一派即可
            sum += nums[i];
            maxVal = Math.max(maxVal, sum); // 更新最大值情况
            if (sum <= 0) {
                sum = 0; // 重置sum
            }
        }
        // 返回最大值
        return maxVal;
    }
}
