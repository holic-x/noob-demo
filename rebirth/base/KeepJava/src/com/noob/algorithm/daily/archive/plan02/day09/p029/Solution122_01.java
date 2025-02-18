package com.noob.algorithm.daily.archive.plan02.day09.p029;

/**
 * 🟡 122 买卖股票的最佳时机II - https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/description/
 */
public class Solution122_01 {

    /**
     * 思路分析：可以买入、卖出股票1次
     * 动态规划思路，记录每天股票不同状态处理下可获得的最大金额
     * int[] dp = new int[2];
     * dp[0] 表示持有股票可获得的最大金额
     * dp[1] 表示不持有股票可获得的最大金额
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // 1.dp 定义（dp[2]）：dp[i][0] 表示持有股票可获得的最大金额；dp[i][1] 表示不持有股票可获得的最大金额
        int[][] dp = new int[n][2];

        /**
         * 2.dp 递推
         * dp[0] : 可由两种情况推导得出 =》max{①,②}
         * - ① 已经持有，继续持有：dp[i][0] = dp[i-1][0]
         * - ② 还未持有，今日买入：dp[i][0] = dp[i - 1][1] - prices[i] // 因为可以买入多次，所以此处是基于前面的未持有状态下买入
         * dp[1] : 可由两种情况推导得出 =》max{③,④}
         * - ③ 已未持有，状态继承：dp[i][1] = dp[i-1][1]
         * - ④ 现持有，今日卖出：dp[i][1] = dp[i-1][0] + prices[i]
         */

        // 3.dp 初始化
        dp[0][0] = -prices[0]; // 第0天持有状态，只能是日内买入
        dp[0][1] = 0; // 第0天未持有状态，没执行操作

        // 4.dp 构建
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        // 返回结果（最后一天卖出股票的时候现金是最多的）
        return dp[n - 1][1];
    }
}
