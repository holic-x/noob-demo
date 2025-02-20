package com.noob.algorithm.daily.archive.plan02.hot100.day07.p021;

/**
 * 🟡 122 买卖股票的最佳时机II - https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/description/
 */
public class Solution122_01 {

    /**
     * 贪心思路：每一日可决定购买、售出股票，最多只能持有1股，可以先购买然后同日售出，返回最大利润
     * profit = p[3]-p[2] + p[2]-p[1] ..... 相当于累加正差价来获取做T利润
     */
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            // 校验差价
            int diff = prices[i] - prices[i - 1];
            // 如果存在正差价则载入利润
            maxProfit += (diff >= 0 ? diff : 0);
        }
        // 返回结果
        return maxProfit;
    }

}
