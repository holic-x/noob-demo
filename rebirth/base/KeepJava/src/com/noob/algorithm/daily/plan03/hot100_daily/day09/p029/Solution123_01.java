package com.noob.algorithm.daily.plan03.hot100_daily.day09.p029;

/**
 * 🟡 123 买卖股票的最佳时机III - https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/description/
 */
public class Solution123_01 {

    /**
     * 思路分析：最多只能2次交易
     * int[][] dp
     * 0: 不执行任何操作
     * => dp[i][0] = dp[i-1][0]
     * 1: 第一次持有
     * => dp[i][1] = max{dp[i-1][0],0 - prices[i]}
     * 2: 第一次不持有
     * => dp[i][2] = max{dp[i-1][1],dp[i][1] + prices[i]}
     * 3: 第二次持有
     * => dp[i][3] = max{dp[i-1][3], dp[i][2] - prices[i]}
     * 4: 第二次不持有
     * => dp[i][4] = max{dp[i-1][4],dp[i][3] + prices[i]}
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][5];

        dp[0][0] = 0;
        dp[0][1] = 0 - prices[0];
        dp[0][2] = 0;
        dp[0][3] = 0 - prices[0]; // 买入卖出再买入
        dp[0][4] = 0;

        // 构建
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = Math.max(dp[i - 1][1], 0 - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i][3] + prices[i]);
        }

        // 不持有股票状态下可得利润最大，因此选择第一次不持有或者第二次不持有状态下的max
        return Math.max(dp[n - 1][2], dp[n - 1][4]);
    }
}
