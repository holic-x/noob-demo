package com.noob.algorithm.dmsxl.leetcode.q123;

import com.noob.algorithm.dmsxl.util.PrintUtil;

/**
 * 123 买卖股票的最佳时机III
 */
public class Solution2 {

    /**
     * 动态规划思路
     */
    public int maxProfit(int[] prices) {
        int m = prices.length;
        // 1.dp[i][5] 构建每一天的不同状态(5种状态)下的最大现金价值
        int[][] dp = new int[m][5];

        /**
         * 2.dp 推导
         * 0:不做任何操作
         * - 直接继承上一状态：dp[i][0]: dp[i][0] = dp[i-1][0]
         * 1:第1次持有
         * - 昨日未持股，今日买入：dp[i][1]= 0-prices[i] // dp[i][1]= dp[i-1][0]-prices[i]也可行，但此处理解为第1次买入股票，起始资金为0更为准确
         * - 昨日已持股，继续持有：dp[i][1]=dp[i-1][1]
         * 2:第1次未持有
         * - 昨日未持股，继承状态：dp[i][2]=dp[i-1][2]
         * - 昨日已持股（第一次买入），今日卖出：dp[i][2] = dp[i-1][1] + prices[i]
         * 3:第2次持有
         * - 昨日未持股(第1次不持有)，今日买入: dp[i][3] = dp[i-1][2] - prices[i]
         * - 昨日已持股，继续持有：dp[i][3]=dp[i-1][3]
         * 4:第2次未持有
         * - 昨日未持股，继承状态：dp[i][4]=dp[i-1][4]
         * - 昨日已持股(第2次持有)，今日卖出：dp[i][4]=dp[i-1][3] + prices[i]
         */

        // 3.dp初始化(dp[0][j]初始化)
        dp[0][0] = 0; // 日内不做任何操作
        dp[0][1] = 0 - prices[0]; // 第1次持有，买入股票
        dp[0][2] = 0; // 第1次不持有，日内执行了买入卖出操作，一正一负抵消
        dp[0][3] = 0 - prices[0]; // 第2次持有，日内执行了第1次买入卖出操作，然后又进行了第2次买入操作
        dp[0][4] = 0; // 第2次不持有，日内分别执行了两次买入卖出操作，一正一负抵消

        // 4.dp构建（根据dp推导公式填充dp）
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = Math.max(0 - prices[i], dp[i - 1][1]); // Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
            dp[i][2] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][2]);
            dp[i][3] = Math.max(dp[i - 1][2] - prices[i], dp[i - 1][3]);
            dp[i][4] = Math.max(dp[i - 1][3] + prices[i], dp[i - 1][4]);
        }

        PrintUtil.printMatrix(dp); // 打印dp数组信息

        // 结果返回（最后一日不持有股票的状态下现金最多）
        return dp[m - 1][4];
    }

    public static void main(String[] args) {
//        int[] prices = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        int[] prices = new int[]{1, 2, 3, 4, 5};
        Solution2 solution2 = new Solution2();
        solution2.maxProfit(prices);
    }

}
