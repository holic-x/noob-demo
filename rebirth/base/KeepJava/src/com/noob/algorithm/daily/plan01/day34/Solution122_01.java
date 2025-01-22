package com.noob.algorithm.daily.plan01.day34;

/**
 * 🟡 122 买卖股票的最佳时机II - https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/description/
 * 要点：可以操作多次股票买卖
 */
public class Solution122_01 {

    /**
     * 贪心思路：
     * 假设在第i天买入，第i+2天卖出，则有prices[i+2]-prices[i] = (prices[i+2]-prices[i+1]) + (prices[i+1] - prices[i])
     * 理论上可以通过收集正利润的方式来不断操作股票以获取最大利润
     */
    public int maxProfit(int[] prices) {
        // 定义最大利润
        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int tProfit = prices[i + 1] - prices[i];
            if (tProfit > 0) {
                // 操作带来正利润才有执行的性价比
                maxProfit += tProfit;
            }
        }
        // 返回结果
        return maxProfit;
    }

}
