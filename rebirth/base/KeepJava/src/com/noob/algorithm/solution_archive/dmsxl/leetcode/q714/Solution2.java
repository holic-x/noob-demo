package com.noob.algorithm.solution_archive.dmsxl.leetcode.q714;

/**
 * 714 买卖股票的最佳时机含手续费
 */
public class Solution2 {

    // 动态规划：空间优化版本
    public int maxProfit(int[] prices, int fee) {
        int m = prices.length;
        // 1.dp定义（dp[2]表示每一天的持有、未持有状态下的最大现金）
        int[] dp = new int[2];

        /**
         * 2.dp推导
         * - `0`：持有状态
         *   - 原未持有（可能是初始，可以是已经买入卖出，选择未持有状态下的最大现金），今日买入（买入不需要处理手续费）：`dp[0]=dp[1]-price[i]`
         *   - 原已持有，继续持有：`dp[0]=dp[0]`
         * - `1`：未持有状态
         *   - 原未持有，继续保持：`dp[1]=dp[1]`
         *   - 原已持有，今日卖出（卖出需处理手续费）：`dp[1]=dp[0]+price[i]-fee`
         */

        // 3.dp初始化(初始化第0天的持有、未持有状态)
        dp[0] = 0 - prices[0]; // 第0天无前置状态，只能是当日买入
        dp[1] = 0; // 第0天无前置状态，保持未持有，初始化为0

        // 4.dp构建
        for (int i = 1; i < m; i++) {
            // dp[0] = Math.max(dp[0], dp[1] - prices[i]);
            // dp[1] = Math.max(dp[1], dp[0] + prices[i] - fee);
            int tempDp0 = dp[0];
            int tempDp1 = dp[1];
            dp[0] = Math.max(tempDp0, tempDp1 - prices[i]);
            dp[1] = Math.max(tempDp1, tempDp0 + prices[i] - fee); // 卖出时处理手续费
        }

        // 返回结果
        return dp[1]; // 卖出时处理手续费，最大利润为最后一天的不持有状态
    }

}
