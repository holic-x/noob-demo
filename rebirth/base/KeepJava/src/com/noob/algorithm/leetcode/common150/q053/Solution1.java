package com.noob.algorithm.leetcode.common150.q053;

/**
 * 53 最大子数组和
 */
public class Solution1 {
    /**
     * 动态规划思路：
     * 1.dp[i] 数组表示以i位置结尾的子数组和的最大值（可以通过累加来更新这个值），初始化
     * 2.状态转移方程：dp[i] = max{dp[i-1]+nums[i],nums[i]}
     *  - dp[i]的选择必须以num[i]结尾，因此其只需要考虑dp[i-1]是否可以给它带来正效益，如果不能就直接切断只保留nums[i]
     */
    public int maxSubArray(int[] nums) {
        // 1.定义dp数组
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        // 2.遍历数组元素，更新值
        for (int i = 1; i < nums.length; i++) {
            // dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if (dp[i - 1] >= 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = Math.max(dp[i - 1], nums[i]);
            }
        }

        // 3.遍历dp数组，获取到dp数组的最大值
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }

        // 返回结果
        return max;
    }
}
