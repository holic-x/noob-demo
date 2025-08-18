package com.noob.algorithm.daily.plan03.hot100_daily.day09.p029;

/**
 * 🟡 122 买卖股票的最佳时机II - https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/description/
 */
public class Solution122_02 {

    /**
     * 思路分析：可多次买卖股票
     * 动态规划：
     * dp[i][0]： 第i天股票状态为持有状态下所能获得的最大利润
     * - 前一天已持有，继续保持 dp[i-1][0]
     * - 前一天未持有，买入股票 dp[i-1][1] - prices[i]
     * dp[i][1]： 第i天股票状态为未持有状态下所能获得的最大利润
     * - 前一天未持有，继续保持 dp[i-1][1]
     * - 前一天已持有，卖出股票 dp[i-1][0] + prices[i]
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        // 初始化dp
        dp[0][0] = -prices[0];
        dp[0][1] = 0;

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }

        // 返回最后一天卖出股票则可获得最大利润
        return dp[n - 1][1];
    }
}
