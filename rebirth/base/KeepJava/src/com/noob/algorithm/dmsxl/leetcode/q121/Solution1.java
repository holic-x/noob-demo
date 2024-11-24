package com.noob.algorithm.dmsxl.leetcode.q121;

/**
 * 121 买卖股票的最佳时机
 */
public class Solution1 {

    /**
     * 思路：贪心算法（低买高卖）
     * 核心：
     * 1.假设自己当日卖出股票，且该股票是在历史最低点买入的情况下所获得的利润差（局部最优）；
     * 2.计算每天基于【假设】所能得到的最大利润（由局部最优->全局最优）
     */
    public int maxProfit(int[] prices) {
        int minHistoryPrice = prices[0]; // 股票的历史最低点(以第1日开始)
        int maxProfit = 0; // 最大利润

        // 遍历（股票需隔日卖出，因此是从第2日开始计算利润）
        for (int i = 1; i < prices.length; i++) {
            // 计算当前利润
            int profit = prices[i] - minHistoryPrice; // 当日卖出价格-历史最低点=当日所得最大利润
            maxProfit = Math.max(maxProfit, profit); // 更新最大利润
            // 更新历史最低点
            minHistoryPrice = Math.min(minHistoryPrice, prices[i]);
        }

        // 返回最大利润
        return maxProfit;
    }
}
