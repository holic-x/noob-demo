package com.noob.algorithm.daily.archive.plan01.day23;

/**
 * 🟢 121 买卖股票的最佳时机
 */
public class Solution121_01 {

    /**
     * 低买高卖：记录历史最低价，计算如果择日以历史最低价卖出的最大获利
     */
    public int maxProfit(int[] prices) {
        int minHistoryPrice = prices[0];
        int maxProfit = 0;

        // 从第2天开始计算
        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - minHistoryPrice); // 更新择日卖出可获得的最大利润
            minHistoryPrice = Math.min(minHistoryPrice, prices[i]); // 记录历史最低价
        }

        // 返回最大利润
        return maxProfit;
    }

}
