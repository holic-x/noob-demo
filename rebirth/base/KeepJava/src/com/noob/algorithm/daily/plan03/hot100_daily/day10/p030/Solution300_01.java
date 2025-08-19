package com.noob.algorithm.daily.plan03.hot100_daily.day10.p030;

import java.util.Arrays;

/**
 * 🟡 300 最长递增子序列（不连续） - https://leetcode.cn/problems/longest-increasing-subsequence/
 */
public class Solution300_01 {

    /**
     * 思路分析：
     * 最长递增子序列：不连续
     * - 元素满足递增条件，从[0,i]位置中选择一个最长的子序列看是否可以接在其后面
     * - 自成一派
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;

        // dp[i] 以i位置元素结尾的最长递增子序列长度

        /**
         * dp 递推
         * - 元素满足递增条件，从[0,i]位置中选择一个最长的子序列看是否可以接在其后面 dp[i] = dp[j] + 1
         * - 这个过程中需要更新dp[i] 进行比较获取到最最长子序列
         */

        // dp 初始化
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // 初始化自身为最长子序列长度

        // dp 构建
        int maxVal = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) { // 表示i可以接在j位置后面
                    dp[i] = Math.max(dp[i], dp[j] + 1); // 更新内部循环过程的max
                }
            }
            maxVal = Math.max(dp[i], maxVal);
        }

        // 返回结果
        return maxVal;
    }

}
