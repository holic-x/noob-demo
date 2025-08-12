package com.noob.algorithm.daily.plan03.hot100_daily.day08.p025;

/**
 * 🟢 746 使用最小花费爬楼梯 - https://leetcode.cn/problems/min-cost-climbing-stairs/description/
 */
public class Solution746_01 {

    /**
     * 思路分析：dp
     * 1.dp定义
     * 2.dp递推
     * 3.dp初始化
     * 4.dp构建
     * 5.结果处理
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        // 1.dp定义：dp[i] 表示到达i阶所需支付的最少费用
        int[] dp = new int[n + 1];

        /**
         * 2.dp递推：每次只能基于当前向上爬1或2个台阶
         * dp[i] = min{dp[i-1]+cost[i-1],dp[i-2]+cost[i-2]}
         */

        // 3.dp初始化
        dp[0] = 0; // 起点无消耗
        dp[1] = 0; // 起点无消耗

        // 4.dp构建
        for (int i = 2; i < n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }

        // 处理结果
        return dp[n];
    }

}
