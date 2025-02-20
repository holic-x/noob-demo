package com.noob.algorithm.leetcode.common150.q053;

/**
 * 53 最大子数组和
 */
public class Solution3 {
    /**
     * 动态规划空间优化版本：Kadane算法
     */
    public int maxSubArray(int[] nums) {
        /**
         * 因为dp[i]只和dp[i-1]、nums[i]相关，因此可以只用一个变量始终指向上一个位置的最大连续子数组和，并同步更新max
         */
        int preMax = 0, max = Integer.MIN_VALUE;
        // 遍历元素，更新
        for (int i = 0; i < nums.length; i++) {
            preMax = Math.max(preMax + nums[i], nums[i]); // 累加和
            max = Math.max(preMax, max);
        }
        // 返回结果
        return max;
    }
}
