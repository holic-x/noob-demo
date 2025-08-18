package com.noob.algorithm.daily.plan03.hot100_daily.day09.p029;

/**
 * 🟡 309 买卖股票的最佳时机含冷冻期 - https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
 */
public class Solution309_01 {

    /**
     * 思路分析：含冷冻期表示卖出股票后无法在第2日买入股票，冷冻期为1天
     * dp[i][0]: 持有状态
     * - 持有状态继续保持：dp[i-1][0]
     * - 未持有状态下（可买入的情况下）买入：max{dp[i-1][1] - prices[i],dp[i-1][3] - prices[i]}
     * dp[i][1]: 未持有状态 未持有状态保持
     * - dp[i-1][1]
     * dp[i][2]: 未持有状态 当日卖出
     * - dp[i-1][0] + prices[i]
     * dp[i][3]: 未持有状态 冷冻期（前一天卖出股票，因此今天处于冷冻期）
     * - dp[i-1][2]
     */
    public int maxProfit(int[] prices) {

        int n = prices.length;
        int[][] dp = new int[n][4];

        dp[0][0] = -prices[0];
        dp[0][1] = 0; // 无前置状态
        dp[0][2] = 0; // 无前置状态
        dp[0][3] = 0; // 无前置状态

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1] - prices[i], dp[i - 1][3] - prices[i]));
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][3]);
            dp[i][2] = dp[i - 1][0] + prices[i];
            dp[i][3] = dp[i - 1][2];
        }

        // 三种未持有状态下的max
        return Math.max(dp[n - 1][1], Math.max(dp[n - 1][2], dp[n - 1][3]));
    }
}
