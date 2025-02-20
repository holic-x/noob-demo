package com.noob.algorithm.plan_archive.plan02.hot100.day09.p029;

/**
 * 🟡 309 买卖股票的最佳时机含冷冻期 - https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
 */
public class Solution309_01 {

    /**
     * 思路分析：含冷冻期表示卖出股票后无法在第2日买入股票，冷冻期为1天
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // 1.dp 定义
        int[][] dp = new int[n][4];

        /**
         * 2.dp 递推
         * 0 持有 dp[i][0] = max{dp[i-1][0],dp[i-1][3] - prices[i],dp[i - 1][2] - prices[i]}
         * 1 未持有（当日卖出）dp[i][1] = dp[i-1][0] + prices[i]
         * 2 未持有（冷冻期）dp[i][2] = dp[i-1][1]
         * 3 未持有（状态保持）dp[i][3] = max{dp[i-1][3],dp[i-1][2]}
         */

        // 3.dp 初始化
        dp[0][0] = 0 - prices[0];
        dp[0][1] = 0;
        dp[0][2] = 0;
        dp[0][3] = 0;

        // 4.dp 构建
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][3] - prices[i], dp[i - 1][2] - prices[i]));
            dp[i][1] = dp[i - 1][0] + prices[i];
            dp[i][2] = dp[i - 1][1];
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2]);
        }

        // 返回最后一天不持有股票的状态
        return Math.max(dp[n - 1][1], Math.max(dp[n - 1][2], dp[n - 1][3]));
    }
}
