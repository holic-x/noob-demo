package com.noob.algorithm.plan_archive.plan01.day23;

import com.noob.algorithm.solution_archive.dmsxl.util.PrintUtil;

/**
 * 🔴123 买卖股票的最佳时机III
 */
public class Solution123_01 {

    /**
     * 动态规划思路
     */
    public int maxProfit(int[] prices) {

        int n = prices.length;

        // 1.dp 定义 dp[i][0]-dp[i][5]
        int[][] dp = new int[n][5];

        /**
         * 2.dp 递推 (dp[i][] 表示第i天持有股票的状态对应的最大利润(现金)：例如可以分为持有、未持有，而根据其前置状态又可以进一步拆分)
         * - dp[i][0]初始化  dp[i][1] 第1次持有  dp[i][2] 第1次未持有  dp[i][3] 第2次持有   dp[i][4] 第2次未持有 , 对于每个状态都要进行更新
         * - 状态① dp[i][0] = dp[i-1][0]
         * - 状态② 昨日未持股，今日买入：dp[i][1] = dp[i-1][0]-prices[i]（或0-prices[0]）; 昨日持股。今日继续保持：dp[i][1]=dp[i-1][1]
         * - 状态③ 昨日未持股，今日继续保持：dp[i][2]=dp[i-1][2]; 昨日持股，今日卖出：dp[i][2] = dp[i-1][1]+prices[i]
         * - 状态④ 昨日未持股，今日买入：dp[i][3] = dp[i-1][2]-prices[i]; 昨日持股。今日继续保持：dp[i][3]=dp[i-1][3]
         * - 状态⑤ 昨日未持股，今日继续保持：dp[i][4]=dp[i-1][4]; 昨日持股，今日卖出：dp[i][4] = dp[i-1][3]+prices[i]
         */

        // 3.dp 初始化
        dp[0][0] = 0;
        dp[0][1] = 0 - prices[0]; // 第一次持股
        dp[0][2] = 0; // 第1次不持有，日内执行了买入卖出操作，一正一负抵消
        dp[0][3] = 0 - prices[0]; // 第2次持有，日内执行了第1次买入卖出操作，然后又进行了第2次买入操作
        dp[0][4] = 0; // 第2次不持有，日内分别执行了两次买入卖出操作，一正一负抵消

        // 4.dp 构建
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
            dp[i][2] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][2]);
            dp[i][3] = Math.max(dp[i - 1][2] - prices[i], dp[i - 1][3]);
            dp[i][4] = Math.max(dp[i - 1][3] + prices[i], dp[i - 1][4]);
        }

        // 打印数组
        PrintUtil.printMatrix(dp);

        // 返回结果
        return dp[n - 1][4]; // 最后一日不持有股票的状态下现金最多
    }

    public static void main(String[] args) {
        int[] prices = new int[]{1, 2, 3, 4, 5};
        Solution123_01 solution = new Solution123_01();
        solution.maxProfit(prices);
    }


}
