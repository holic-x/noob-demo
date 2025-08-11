package com.noob.algorithm.daily.plan03.hot100_daily.day07.p021;

/**
 * 🟡 122 买卖股票的最佳时机II - https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/description/
 */
public class Solution122_01 {

    /**
     * 思路分析：贪心思路（收集正利润概念）
     * profit[2] = (p[1]-p[0]) + (p[2]-[1])
     * =》转化为收集正利润的思路
     */
    public int maxProfit(int[] prices) {

        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            // 如果出现利润差则可收集
            int diff = prices[i] - prices[i - 1];
            if (diff > 0) {
                maxProfit += diff;
            }

        }

        return maxProfit;
    }

}
