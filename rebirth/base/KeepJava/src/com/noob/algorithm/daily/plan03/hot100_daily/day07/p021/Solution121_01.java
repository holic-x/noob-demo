package com.noob.algorithm.daily.plan03.hot100_daily.day07.p021;

/**
 * 🟡 121 买卖股票的最佳时机I - https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/description/
 */
public class Solution121_01 {

    /**
     * 思路分析：
     * 贪心思路：低买高卖
     */
    public int maxProfit(int[] prices) {
        // 思路：记录当前可买入的历史最低价，并计算如果当日卖出可获得的最大利润
        int minHistoryPrice = prices[0];
        int maxProfit = 0;

        // 当日买入只能隔日卖出
        for (int i = 1; i < prices.length; i++) {
            int curProfit = prices[i] - minHistoryPrice;
            // 更新最大利润
            maxProfit = Math.max(curProfit, maxProfit);
            // 更新已经出现的历史最低价
            minHistoryPrice = Math.min(prices[i], minHistoryPrice);
        }

        // 返回最大利润
        return maxProfit;
    }
}
