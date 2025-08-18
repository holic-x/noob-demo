package com.noob.algorithm.daily.plan03.hot100_daily.day09.p029;

/**
 * 🟢 121 买卖股票的最佳时机 - https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/description/
 */
public class Solution121_01 {

    /**
     * 思路分析：贪心思路（低买高卖系列）
     * 1.记录历史最低价，假设是以历史最低价买入
     * 2.预估最大利润：假设以当日价格卖出，以历史最低价买入所能获得的最大利润
     */
    public int maxProfit(int[] prices) {
        int minHistoryPrice = prices[0]; // Integer.MAX_VALUE
        int maxProfit = 0;

        // 处理
        for (int i = 1; i < prices.length; i++) {
            // 处理最大利润
            maxProfit = Math.max(maxProfit, prices[i] - minHistoryPrice);
            // 更新历史最低价（为后续的利润计算做基础）
            minHistoryPrice = Math.min(minHistoryPrice, prices[i]);
        }

        // 返回最大利润
        return maxProfit;
    }

}
