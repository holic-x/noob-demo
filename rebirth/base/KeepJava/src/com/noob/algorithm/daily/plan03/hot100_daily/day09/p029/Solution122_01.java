package com.noob.algorithm.daily.plan03.hot100_daily.day09.p029;

/**
 * 🟡 122 买卖股票的最佳时机II - https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/description/
 */
public class Solution122_01 {

    /**
     * 思路分析：
     * 贪心思路：收集正利润
     */
    public int maxProfit(int[] prices) {

        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            int curSub = prices[i] - prices[i - 1];
            if (curSub >= 0) {
                maxProfit += curSub;
            }
        }

        return maxProfit;
    }
}
