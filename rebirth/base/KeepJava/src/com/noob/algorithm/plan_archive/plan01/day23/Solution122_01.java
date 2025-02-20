package com.noob.algorithm.plan_archive.plan01.day23;

/**
 * 🟡 122 买卖股票的最佳时机II - https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/description/
 */
public class Solution122_01 {

    /**
     * 正利润累加：记录每个区间的正利润，如果存在则进行收集
     */
    public int maxProfit(int[] prices) {
        // 记录最大利润值
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - prices[i - 1];
            if (profit >= 0) {
                maxProfit += profit;
            }
        }
        // 返回结果
        return maxProfit;
    }


}
