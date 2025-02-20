package com.noob.algorithm.solution_archive.dmsxl.leetcode.q714;

/**
 * 714 买卖股票的最佳时机含手续费
 */
public class Solution1 {

    // 动态规划
    public int maxProfit(int[] prices, int fee) {
        int m = prices.length;
        // 1.dp定义（dp[i][2]表示每一天的持有、未持有状态下的最大现金）
        int[][] dp = new int[m][2];

        /**
         * 2.dp推导
         * - `0`：持有状态
         *   - 原未持有（可能是初始，可以是已经买入卖出，选择未持有状态下的最大现金），今日买入（买入不需要处理手续费）：`dp[i][0]=dp[i-1][1]-price[i]`
         *   - 原已持有，继续持有：`dp[i][0]=dp[i-1][0]`
         * - `1`：未持有状态
         *   - 原未持有，继续保持：`dp[i][1]=dp[i-1][1]`
         *   - 原已持有，今日卖出（卖出需处理手续费）：`dp[i][1]=dp[i-1][0]+price[i]-fee`
         */

        // 3.dp初始化(初始化第0天的持有、未持有状态)
        dp[0][0] = 0 - prices[0]; // 第0天无前置状态，只能是当日买入
        dp[0][1] = 0; // 第0天无前置状态，保持未持有，初始化为0

        // 4.dp构建
        for (int i = 1; i < m; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee); // 卖出时处理手续费
        }

        // 返回结果
        return dp[m - 1][1]; // 卖出时处理手续费，最大利润为最后一天的不持有状态
    }

}
