package com.noob.algorithm.dmsxl.leetcode.q121;

import com.noob.algorithm.dmsxl.util.PrintUtil;

/**
 * 121 买卖股票的最佳时机
 */
public class Solution3 {

    /**
     * 思路：动态规划(空间优化版本)
     * dp[0]: 表示持有股票所得最大现金
     * dp[1]: 表示不持有股票所得最大现金
     */
    public int maxProfit(int[] prices) {
        int m = prices.length;
        // 1.dp 定义（dp[0]持有、dp[1]不持有）
        int[] dp = new int[2];

        /**
         * 2.推导公式
         * 2.1 dp[0] 第`i`天持有股票所得最大现金
         * - 昨日已持有，今日继续持有：dp[0] = dp[0]（股票只能买入一次，继承昨日的状态）
         * - 昨日未持有，今日买入持有：dp[0] = -price (只能买入一次，因此是初始持有现金减去当日买入股票的价格，为所得现金)
         * 2.2 dp[1] 第`i`天不持有股票所得最大现金
         * - 昨日已未持有，今日无操作：dp[1] = dp[1] (股票只能卖出一次，如果昨日已经卖出，则今日无操作，继承昨日的状态)
         * - 昨日还持有，今日卖出：dp[1] = dp[0] + price[i](如果昨日还持有，选择今日卖出，则所得现金为【昨日持有状态下所得最大现金】+【当日卖出价格】)
         */

        // 3.dp 初始化（dp[0]、dp[0] : 初始化第0天持有、不持有股票所能获得的最大现金）
        dp[0] = 0 - prices[0]; // 第0天持有股票，则只能是当日买入（前面没有可推导的基础）
        dp[1] = 0; // 第0天不持有股票，现金为初始状态
        PrintUtil.print(dp); // 打印状态变化

        // 4.构建dp
        for (int i = 1; i < m; i++) {
            // 1.计算【第`i`天持有股票所得最大现金】
            dp[0] = Math.max(dp[0], -prices[i]);

            // 2.计算【第`i`天不持有股票所得最大现金】
            dp[1] = Math.max(dp[1], dp[0] + prices[i]);

            // 打印状态变化
            PrintUtil.print(dp);
        }

        // 返回结果
        return dp[1]; // 不持有股票的状态所得金钱一定更多，因此最后一天的不持有股票时所得现金一定是最多的
    }


    public static void main(String[] args) {
        int[] price = new int[]{7, 1, 5, 3, 6, 4};
        Solution3 solution2 = new Solution3();
        solution2.maxProfit(price);
    }
}
