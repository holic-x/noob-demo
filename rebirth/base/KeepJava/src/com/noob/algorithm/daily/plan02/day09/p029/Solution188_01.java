package com.noob.algorithm.daily.plan02.day09.p029;

/**
 * 🔴 188 买卖股票的最佳时机IV - https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/description/
 */
public class Solution188_01 {

    /**
     * k 次 买入卖出
     * dp[i][0] 默认状态
     * dp[i][1] 第1次持有 dp[i][1] = max{dp[i-1][1],dp[i-1][0] - prices[i]}
     * dp[i][2] 第1次不持有 dp[i][2] = max{dp[i-1][2],dp[i-1][1] + prices[i]}
     * dp[i][3] 第2次持有 dp[i][3] = max{dp[i-1][3],dp[i-1][2] - prices[i]}
     * dp[i][4] 第2次不持有 dp[i][4] = max{dp[i-1][4],dp[i-1][3] + prices[i]}
     * <p>
     * 第k次：共2K+1个状态
     * dp[i][2k-1] 第k次持有 dp[i][2k-1] = max{dp[i-1][2k-1],dp[i-1][2k-2] - prices[i]}
     * dp[i][2k] 第k次不持有 dp[i][2k] = max{dp[i-1][2k],dp[i-1][2k-1] + prices[i]}
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        // 1.dp 定义
        int[][] dp = new int[n][2 * k + 1];

        /**
         * 2.dp 递推
         */

        // 3.dp 初始化 dp[0][0...k...2k]  此处遍历的维度是对完整的状态范围
        for (int i = 0; i < 2 * k + 1; i++) {
            if (i == 0) {
                dp[0][0] = 0;
            } else if (i % 2 == 1) {
                dp[0][i] = -prices[0]; // 表示持有股票状态下（日内买入卖出抵消，最终保留持有股票状态）
            }
            if (i % 2 == 0) {
                dp[0][i] = 0; // 表示未持有股票状态下（日内买入卖出抵消，最终保留不持有股票状态）
            }
        }
        /*
        // 此处遍历的维度是针对次数k然后做相应转化（转化未对应的状态）
        dp[0][0] = 0; // 日内不做任何操作
        for (int x = 1; x <= k; x++) {
            dp[0][2 * x - 1] = 0 - prices[0]; // 第k次持有，买入股票
            dp[0][2 * x] = 0; // 第k次不持有，日内执行了买入卖出操作，一正一负抵消
        }
         */

        // 4.dp 构建
        for (int i = 1; i < n; i++) {
            // 此处的遍历维度是次数k
            for (int x = 1; x <= k; x++) {
                dp[i][0] = 0;
                dp[i][2 * x - 1] = Math.max(dp[i - 1][2 * x - 1], dp[i - 1][2 * x - 2] - prices[i]);
                dp[i][2 * x] = Math.max(dp[i - 1][2 * x], dp[i - 1][2 * x - 1] + prices[i]);
            }
        }

        // 返回最后一天未持有股票状态下的最大值
        return dp[n - 1][2 * k];
    }
}
