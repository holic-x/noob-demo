package com.noob.algorithm.dmsxl.leetcode.q122;

/**
 * 122 买卖股票的最佳时机II
 */
public class Solution1 {

    /**
     *  每日可操作股票，但最多只能持有一股
     */
    public int maxProfit(int[] prices) {
        /**
         * 贪心思路：收集正利润，如果有利润就做T，没利润就不动
         * nums[2]-nums[0] = (nums[2]-nums[1]) + (nums[1]-nums[0])
         * 即可以理解为假设每天做T能得到正利润，那么就去做，如果不能则继续持有等待，进而使得这个数值最大
         */
        int maxProfit = 0; // 初始化最大利润

        // 计算每日做T利润，如果存在正利润就累加，不存在正利润就继续持有
        for(int i=1;i<prices.length;i++){ // 第1天没有利润
            int curProfit = prices[i]-prices[i-1];
            if(curProfit>0){
                maxProfit += curProfit; // 累加正利润（做T）
            }
        }

        // 返回结果
        return maxProfit;
    }
}
