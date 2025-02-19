package com.noob.algorithm.daily.plan02.day10.p030;

import java.util.Arrays;

/**
 * 🟢 674 最长连续递增序列 - https://leetcode.cn/problems/longest-continuous-increasing-subsequence/description/
 */
public class Solution674_01 {

    /**
     * 思路分析：动态规划
     */
    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length;
        // 1.dp 定义（dp[i] 表示以位置i元素结尾的最长连续递增序列的长度）
        int[] dp = new int[n];

        /**
         * 2.dp 递推：dp[i] 的推导和其前面位置的元素有关
         * 如果nums[i]>nums[i-1] 说明可以拼在后面构成更长的连续递增序列 => dp[i] = dp[i-1] + 1
         * 如果nums[i]<=nums[i-1] 说明无法拼接构成连续递增，只能自成一派 => dp[i] = 1
         */

        // 3.dp 初始化
        Arrays.fill(dp, 1);

        // 4.dp 构建
        int maxSubLen = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = (nums[i] > nums[i - 1] ? (dp[i - 1] + 1) : 1);
            // 更新最长连续递增序列
            maxSubLen = Math.max(maxSubLen, dp[i]);
        }

        // 返回结果
        return maxSubLen;
    }
}
