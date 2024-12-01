package com.noob.algorithm.dmsxl.leetcode.q122;

import com.noob.algorithm.dmsxl.util.PrintDPUtil;

/**
 * 122 买卖股票的最佳时机II
 */
public class Solution3 {

    /**
     * 思路：动态规划 空间优化版本
     * dp[0]: 表示持有股票所得最大现金
     * dp[1]: 表示不持有股票所得最大现金
     */
    public int maxProfit(int[] prices) {
        int m = prices.length;
        // 1.dp 定义（dp[0]持有、dp[1]不持有）
        int[] dp = new int[2];

        /**
         * 2.推导公式
         * 2.1 dp[0] 持有股票所得最大现金
         * - 昨日已持有，今日继续持有：dp[0] = dp[0]（不能重复持有，继承昨日的状态）
         * - 昨日未持有，今日买入持有：dp[0] = dp[1] - price (由于可以重复买入卖出操作，所以此处不一定是第一次买入，因此选择【昨日不持有股票状态的最大现金】-【当日价格】)
         * 2.2 dp[1] 不持有股票所得最大现金
         * - 昨日已未持有，今日无操作：dp[1] = dp[1] (如果昨日已经卖出，则今日无操作，继承昨日的状态)
         * - 昨日还持有，今日卖出：dp[1] = dp[0] + price[i](如果昨日还持有，选择今日卖出，则所得现金为【昨日持有状态下所得最大现金】+【当日卖出价格】)
         */

        // 3.dp 初始化（dp[0]\dp[1]是推导基础）
        dp[0] = 0 - prices[0]; // 第0天持有股票，则只能是当日买入（前面没有可推导的基础）
        dp[1] = 0; // 第0天不持有股票，现金为初始状态
        PrintDPUtil.print(dp); // 打印dp数组

        // 4.构建dp
        for (int i = 1; i < m; i++) {
            // 1.计算【持有股票所得最大现金】
            dp[0] = Math.max(dp[0], dp[1] - prices[i]);// todo 和【121】问题处理唯一不同的地方

            // 2.计算【不持有股票所得最大现金】
            dp[1] = Math.max(dp[1], dp[0] + prices[i]);

            PrintDPUtil.print(dp); // 打印dp数组
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
