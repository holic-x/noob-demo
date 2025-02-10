package com.noob.algorithm.daily.archive.plan01.day34;

/**
 * 🟢 121 买卖股票的最好时机 - https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/description/
 * 要点：只能操作一次股票买卖
 */
public class Solution121_01 {
    /**
     * 思路：低买高卖
     * 贪心思路：设定自己是在以往的最低价买入，计算每日卖出可获得的最大利润
     */
    public int maxProfit(int[] prices) {

        // 初始化历史最低价位
        int minHistoryPrice = prices[0];
        // 定义最大利润
        int maxProfit = 0;
        // 从第2天开始才能卖出，计算以历史低价买入、当日价格卖出所能获得的最大利润
        for (int i = 1; i < prices.length; i++) {
            // 计算当日可获得利润，并更新最大利润
            maxProfit = Math.max(maxProfit, prices[i] - minHistoryPrice);
            // 更新历史最低价
            minHistoryPrice = Math.min(minHistoryPrice, prices[i]);
        }
        // 返回结果
        return maxProfit;

    }

}
