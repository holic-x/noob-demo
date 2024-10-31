package com.noob.algorithm.common150.q122;

/**
 * 122 买卖股票的最佳时机II
 */
public class Solution2 {
    // 每日可操作股票，但最多只能持有一股
    public int maxProfit(int[] prices) {
        /**
         * 贪心思路：收集正利润，如果有利润就做T，没利润就不动
         * nums[2]-nums[0] = (nums[2]-nums[1]) + (nums[1]-nums[0])
         * 即可以理解为假设每天做T能得到正利润，那么就去做，如果不能则继续持有等待，进而使得这个数值最大
         */
        // 定义最大利润值
        int maxProfit = 0;
        // 第1天没有利润，从第2天开始操作
        for(int i = 1; i < prices.length; i++) {
            // 判断是否能得到正利润
            int t = prices[i] - prices[i-1];
            if(t>0){
                maxProfit += t; // 累加正利润
            }
        }
        // 返回结果
        return maxProfit;
    }
}
