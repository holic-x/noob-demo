package com.noob.algorithm.daily.codeTop;

/**
 * 🟡 122 买卖股票的最佳时机
 */
public class Solution122_01 {

    /**
     * 思路分析：贪心思路（累加正价格差）
     * p[3] - p[0] = (p[3]-p[2]) + (p[2]-p[1]) + (p[1]-p[0])
     * 基于这种思路，只需要收集相邻的正利润，即可获得最大的利润
     */
    public int maxProfit(int[] prices) {
        int maxProfit = 0;

        for(int i=1;i<prices.length;i++){
            // 如果存在利润差则做T
            if(prices[i]-prices[i-1]>0){
                maxProfit += (prices[i]-prices[i-1]);
            }
        }

        // 返回最大利润
        return maxProfit;
    }

}
