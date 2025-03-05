package com.noob.algorithm.daily.codeTop;

/**
 * 🟡 122 买卖股票的最佳时机
 */
public class Solution122_02 {

    /**
     * 思路分析：动态规划思路
     * 定义dp[i][2] 用于表示第i天不同持有股票状态的所能获得最大金额
     * - dp[i][0] 未持有股票
     * - dp[i][1] 持有股票
     * 基于此推导每天的不同状态下的所能获得的最大金额
     */
    public int maxProfit(int[] prices) {
        int m = prices.length;
        // 1.dp[i][2]：dp[i][0] 表示未持有股票状态，dp[i][1] 表示持有股票状态
        int[][] dp = new int[m][2];

        /**
         * 2.dp 推导
         * - ① 未持有股票状态
         * - 本身未持有，继续保持：dp[i][0] = dp[i-1][0]
         * - 原持有，今日卖出：dp[i][0] = dp[i-1][1] + prices[i]
         *
         * - ② 持有股票状态
         * - 本身持有，继续保持：dp[i][1] = dp[i-1][1]
         * - 原未持有，今日买入：dp[i][1] = dp[i-1][0] - prices[i]
         */

        // 3.dp 初始化
        dp[0][0] = 0; // 无前置状态，当日无操作
        dp[0][1] = 0 - prices[0]; // 只能是今日买入

        // 4.dp 构建
        for (int i = 1; i < m; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        // 返回结果（最后一天未持有股票状态为所能获得利润的最大）
        return dp[m-1][0];
    }

}
