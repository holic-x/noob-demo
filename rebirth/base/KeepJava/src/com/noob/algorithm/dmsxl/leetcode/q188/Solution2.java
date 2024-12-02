package com.noob.algorithm.dmsxl.leetcode.q188;

import com.noob.algorithm.dmsxl.util.PrintDPUtil;

/**
 * 188 买卖股票的最佳时机IV
 */
public class Solution2 {

    /**
     * 动态规划思路:空间优化版本
     */
    public int maxProfit(int k, int[] prices) {
        int m = prices.length;
        // 1.dp[2*k+1] 构建每一天的不同状态(2k+1种状态:0、k次持有、k次不持有)下的最大现金价值
        int[] dp = new int[2 * k + 1];

        /**
         * 2.dp 推导
         * 0:不做任何操作
         * - 直接继承上一状态：dp[0]: dp[0] = dp[0]
         * 第k次：第k次持有（2k-1）、第k次未持有（2k） k=1 开始
         * 1:第1次持有
         * - 昨日未持股，今日买入：dp[1]= dp[0]-prices[i] // dp[1]= dp[0]-prices[i]也可行，但此处理解为第1次买入股票，起始资金为0更为准确
         * - 昨日已持股，继续持有：dp[1]=dp[1]
         * 2:第1次未持有
         * - 昨日未持股，继承状态：dp[2]=dp[2]
         * - 昨日已持股（第一次买入），今日卖出：dp[2] = dp[1] + prices[i]
         * 3:第k次持有
         * - 昨日未持股(第k-1次不持有)，今日买入: dp[2*k-1] = dp[2*k-2] - prices[i]
         * - 昨日已持股，继续持有：dp[2*k-1]=dp[2*k-1]
         * 4:第k次未持有
         * - 昨日未持股，继承状态：dp[2*k]=dp[2*k]
         * - 昨日已持股(第k次持有)，今日卖出：dp[2*k]=dp[2*k-1] + prices[i]
         */

        // 3.dp初始化(dp[0][j]初始化)
        dp[0] = 0; // 日内不做任何操作
        for (int x = 1; x <= k; x++) {
            dp[2 * x - 1] = 0 - prices[0]; // 第k次持有，买入股票
            dp[2 * x] = 0; // 第k次不持有，日内执行了买入卖出操作，一正一负抵消
        }
        PrintDPUtil.print(dp); // 打印dp数组信息

        // 4.dp构建（根据dp推导公式填充dp）
        for (int i = 1; i < m; i++) {
            dp[0] = dp[0]; // 不操作
            // k 次 操作封装
            for (int x = 1; x <= k; x++) {
                dp[2 * x - 1] = Math.max(dp[2 * x - 2] - prices[i], dp[2 * x - 1]); // 第k次持有
                dp[2 * x] = Math.max(dp[2 * x - 1] + prices[i], dp[2 * x]); // 第k次不持有
            }
            PrintDPUtil.print(dp); // 打印dp数组信息
        }

        // 结果返回（最后一日不持有股票的状态下现金最多）
        return dp[2 * k];
    }

    public static void main(String[] args) {
//        int[] prices = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        int[] prices = new int[]{1, 2, 3, 4, 5};
        Solution2 solution2 = new Solution2();
        solution2.maxProfit(2, prices);
    }

}