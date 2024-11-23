package com.noob.algorithm.dmsxl.leetcode.q070;

/**
 * 070-爬楼梯
 */
public class Solution1 {

    // 动态规划
    public int climbStairs(int n) {
        // 1.定义dp（dp[i]表示爬到第i阶的方案）
        int[] dp = new int[n + 1]; // 此处要计算n下标，则数组需要加长
        // 2.递推公式：dp[i] = dp[i-1] + dp[i-2]
        // 3.初始化dp
        dp[0] = 1;
        dp[1] = 1;
        // 4.构建dp
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        // 返回结果
        return dp[n];
    }

}
