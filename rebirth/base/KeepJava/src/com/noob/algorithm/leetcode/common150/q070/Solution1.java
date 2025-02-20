package com.noob.algorithm.leetcode.common150.q070;

/**
 * 070 爬楼梯
 */
public class Solution1 {
    // 动态规划
    public int climbStairs(int n) {
        // 1.dp 数组定义(dp[i]表示爬i阶的方案个数)
        int[] dp = new int[n+1];
        // 2.初始化dp
        dp[0] = 1; // 爬0阶有1种方案
        dp[1] = 1; // 爬1阶有1种方案
        // 3.构建dp
        for(int i=2;i<=n;i++){
            // 公式：爬N阶的方案=爬N-1的方案+爬N-2的方案
            dp[i] = dp[i-1] + dp[i-2];
        }
        // 4.返回所求答案
        return dp[n];
    }
}
