package com.noob.algorithm.solution_archive.dmsxl.leetcode.q746;

/**
 * 746 使用最小花费爬楼梯
 */
public class Solution1 {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        // 1.构建dp（dp[i]表示爬到第i阶的最小消耗）
        int[] dp = new int[n + 1];
        // 2.递推公式dp[i] = min{dp[i-1]+cost[i-1],dp[i-2]+cost[i-2]}

        // 3.初始化dp
        dp[0] = 0; // 起点无消耗
        dp[1] = 0; // 起点无消耗

        // 4.构建dp
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }

        // 返回到达顶楼的最低消耗
        return dp[n];
    }
}
