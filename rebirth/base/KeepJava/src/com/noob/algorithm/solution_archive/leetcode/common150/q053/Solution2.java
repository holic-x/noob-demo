package com.noob.algorithm.solution_archive.leetcode.common150.q053;

/**
 * 53 最大子数组和
 */
public class Solution2 {

    /**
     * 动态规划思路：
     * 1.dp[i] 数组表示以i位置结尾的子数组和的最大值（可以通过累加来更新这个值），初始化
     * 2.状态转移方程：dp[i] = max{dp[i-1]+nums[i],nums[i]}
     */
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int curMaxSum = 0;

        for (int i = 1; i < nums.length; i++) {
            curMaxSum = Math.max(curMaxSum + nums[i], nums[i]);
            max = Math.max(max, curMaxSum);
        }

        // 返回结果
        return max;
    }

}
