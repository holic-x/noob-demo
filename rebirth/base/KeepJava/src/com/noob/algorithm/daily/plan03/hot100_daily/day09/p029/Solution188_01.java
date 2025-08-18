package com.noob.algorithm.daily.plan03.hot100_daily.day09.p029;

/**
 * 🔴 188 买卖股票的最佳时机IV - https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/description/
 */
public class Solution188_01 {

    /**
     * 思路分析：
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
     * <p>
     * ........
     * 第k次持有：2*(k-1) + 1 =>2*k-1
     * => dp[i][2*k-1] = max{dp[i-1][2*k-1], dp[i-1][2*k-2] - prices[i]}
     * 第k次不持有：2*k
     * => dp[i][2*k] = max{dp[i-1][2*k], dp[i-1][2*k-1] + prices[i]}
     */
    public int maxProfit(int k, int[] prices) {

        int n = prices.length;
        int[][] dp = new int[n][2 * k + 1];

        dp[0][0] = 0;
        for (int i = 1; i <= k; i++) {
            dp[0][2 * i - 1] = -prices[0];
            dp[0][2 * i] = 0;
        }

        // 构建dp
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0];
            for (int j = 1; j <= k; j++) {
                dp[i][2 * j - 1] = Math.max(dp[i - 1][2 * j - 1], dp[i - 1][2 * j - 2] - prices[i]);
                dp[i][2 * j] = Math.max(dp[i - 1][2 * j], dp[i - 1][2 * j - 1] + prices[i]);
            }
        }

        return dp[n - 1][2 * k];

    }
}
