package com.noob.algorithm.dmsxl.leetcode.q309;

import com.noob.algorithm.dmsxl.util.PrintUtil;

/**
 * 309 股票交易的最佳时机含冷冻期
 */
public class Solution2 {

    // 动态规划（空间优化版本）
    public int maxProfit(int[] prices) {
        int m = prices.length;

        // 1.定义dp（dp[4]:每一天不同状态下的剩余的最大现金价值）
        int[] dp = new int[4];

        /**
         * 2.dp推导
         * 0-持有
         * - 原已持有，继续保持:dp=dp
         * - 原未持有，今日买入:
         * - - 1->0: dp=dp[1]-price[i]
         * - - 3->0: dp=dp[3]-price[i]
         *
         * 1-不持有（状态保持）
         * - 前两日卖出，过了冷冻期（2->1）:dp[1]=dp[1]
         * - 昨日是冷冻期（3->1）：dp[1]=dp[3]
         *
         * 2-不持有（今日卖出）
         * - 原已持有，今日卖出（0->2）：dp[2]=dp+price[i]
         *
         * 3-冷冻期
         * - 昨日卖出，今日为冷冻期（2->3）：dp[3]=dp[2](冷冻期只有1日，继续保持前一日【当日卖出】的最大现金)
         */

        // 3.初始化dp（初始化第0天的4个初始状态）
        dp[0] = 0 - prices[0]; // 无前置推导，只能是今日买入
        dp[1] = 0; // 无前置推导，只能是初始为0
        dp[2] = 0; // 无前置推导，只能是初始为0
        dp[3] = 0; // 无前置推导，只能是初始为0
        PrintUtil.print(dp); // 打印dp数组

        // 4.构建dp（根据递推公式进行处理）
        for (int i = 1; i < m; i++) {
            // 需要用临时变量存储dp[0]、dp[2] 因为从前往后遍历会覆盖掉后面要使用的dp[0]、dp[2]
            int tempDp0 = dp[0];
            int tempDp2 = dp[2];
            // 递推填充
            dp[0] = Math.max(dp[0], Math.max(dp[1] - prices[i], dp[3] - prices[i]));
            dp[1] = Math.max(dp[1], dp[3]);
            dp[2] = tempDp0 + prices[i]; // dp[0] + prices[i];
            dp[3] = tempDp2; // dp[2];
            PrintUtil.print(dp); // 打印dp数组
        }

        // 返回结果(从3种卖出状态中选择最大)
        return Math.max(dp[3], Math.max(dp[1], dp[2]));
    }

    public static void main(String[] args) {
        int[] prices = new int[]{1, 2, 3, 0, 2};
        Solution2 solution1 = new Solution2();
        solution1.maxProfit(prices);
    }

}
