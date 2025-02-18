package com.noob.algorithm.daily.plan02.day09.p029;

/**
 * 🟡 123 买卖股票的最佳时机III - https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/description/
 */
public class Solution123_01 {

    /**
     * 最多可以做2笔交易
     * dp[i][0] 默认状态
     * dp[i][1] 第1次持有 dp[i][1] = max{dp[i-1][1],dp[i-1][0] - prices[i]}
     * dp[i][2] 第1次不持有 dp[i][2] = max{dp[i-1][2],dp[i-1][1] + prices[i]}
     * dp[i][3] 第2次持有 dp[i][3] = max{dp[i-1][3],dp[i-1][2] - prices[i]}
     * dp[i][4] 第2次不持有 dp[i][4] = max{dp[i-1][4],dp[i-1][3] + prices[i]}
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // 1.dp 定义
        int[][] dp = new int[n][5];

        /**
         * 2.dp 推导
         */

        // 3.dp 初始化
        dp[0][0] = 0;
        dp[0][1] = 0 - prices[0]; // 只能是买入
        dp[0][2] = 0; // 日内分别执行1次买入卖出
        dp[0][3] = 0 - prices[0]; // 日内分别执行1次买入卖出，然后执行第2次买入
        dp[0][4] = 0; // 日内分别执行2次买入卖出

        // 4.dp 构建
        for (int i = 1; i < n; i++) {
            dp[i][0] = 0;
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }

        // 返回结果（不持有股票的情况下可获得最大金额）
        return Math.max(dp[n - 1][2], dp[n - 1][4]);
    }
}
