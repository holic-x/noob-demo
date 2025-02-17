package com.noob.algorithm.daily.archive.plan02.day07.p021;

/**
 * 🟡 121 买卖股票的最佳时机I - https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/description/
 */
public class Solution121_01 {

    // 贪心思路：低买高卖
    public int maxProfit(int[] prices) {

        int minHistoryPrice = prices[0];
        int maxProfit = 0; // 最大利润

        // 只能从第2日开始卖出
        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - minHistoryPrice; // 选择以历史最低价买入、第i日卖出可获得的利润
            // 校验当日卖出的利润，更新最大利润和历史最低价
            minHistoryPrice = Math.min(minHistoryPrice, prices[i]);
            maxProfit = Math.max(maxProfit, profit);
        }

        // 返回最大利润
        return maxProfit;
    }
}
