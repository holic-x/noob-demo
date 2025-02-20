package com.noob.algorithm.daily.archive.plan02.hot100.day09.p029;

/**
 * 🟡 714 买卖股票的最佳时机含手续费 - https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/
 */
public class Solution714_02 {

    /**
     * 思路分析：多次交易股票，每笔交易都需要支付手续费（可选择买入的时候支付，或者卖出的时候支付，一次买入卖出只需要支付1次手续费即可）
     * dp[0] 持有状态下的最大利润
     * dp[1] 不持有状态下的最大利润
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        // 1.dp 定义
        int[][] dp = new int[n][2];

        /**
         * 2.dp 递推 (假设设定在卖出股票的时候结算手续费)
         * dp[i][0] = max{dp[i-1][0],dp[i-1][1] - prices[i]}
         * dp[i][1] = max{dp[i-1][1],dp[i-1][0] + prices[i]}
         */

        // 3.dp 初始化
        dp[0][0] = 0 - prices[0];
        dp[0][1] = 0;// 卖出的时候结算手续费，初次未持有状态没做任何操作不需要交手续费

        // 4.dp 构建
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
        }

        // 返回最大利润（最后一天未持有股票状态）
        return dp[n - 1][1];
    }

}
