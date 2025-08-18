package com.noob.algorithm.daily.plan03.hot100_daily.day09.p029;

/**
 * 🟡 714 买卖股票的最佳时机含手续费 - https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/
 */
public class Solution714_02 {

    /**
     * 思路分析：一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费
     * - 可选择在买入的时候就计算手续费或者卖出的时候计算手续费
     */
    public int maxProfit(int[] prices, int fee) {
        /**
         * dp[i][0] 表示第i天股票状态为持有态
         * dp[i][1] 表示第i天股票状态为非持有态
         */
        int n = prices.length;
        int[][] dp = new int[n][2];

        // 初始化dp
        dp[0][0] = 0 - prices[0]; // 卖出的时候计算手续费
        dp[0][1] = 0;

        // 构建dp
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee); // 卖出的时候计算手续费
        }
        // 返回最大利润
        return dp[n - 1][1];
    }

}
