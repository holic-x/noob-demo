package com.noob.algorithm.common150.q121;

/**
 * 121 买卖股票的最佳时机
 */
public class Solution1 {

    /**
     * 贪心算法：记录股票的历史低价，以及股票隔日卖出时可以获得利益（更新max）
     */
    public int maxProfit(int[] prices) {
        // 记录利益的最大值
        int maxProfit = 0; // Integer.MIN_VALUE（此处设定利润初始化为0,后续利润是负数也没办法卖）
        // 记录股票价格的历史最低价
        int minHisPrice = prices[0]; // 默认第1天为最低价，后续遍历过程中更新最低价
        // 遍历数组元素
        for(int i=1;i<prices.length;i++) {
            // 假设股票当前卖出，计算这个利润值，并对比是否可获得最大利益（更新目前的最大收益）
            maxProfit = Math.max(maxProfit,prices[i] - minHisPrice);
            // 更新历史最低价
            minHisPrice = Math.min(minHisPrice,prices[i]);
        }
        // 返回结果
        return maxProfit;
    }

}
