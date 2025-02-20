package com.noob.algorithm.daily.archive.plan02.hot100.day08.p025;

/**
 * 🟢 746 使用最小花费爬楼梯 - https://leetcode.cn/problems/min-cost-climbing-stairs/description/
 */
public class Solution746_01 {

    /**
     * 思路分析：从第i个台阶向上爬需要支付的费用
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        // 1.dp定义 dp[i] 表示到达第i个台阶所需支付的最少费用
        int[] dp = new int[n + 1];
        /**
         * 2.dp 递推，每层只能向上爬1个台阶或者2个台阶，因此dp[i]需要从这两种方案中选择花费最少的
         * - dp[i] = min{第i-1层花费cost[i-1]到达i，第i-2层花费cost[i-2]到达i} = min{dp[i-1]+cost[i-1],dp[i-1]+cost[i-2]}
         */

        // 3.dp 初始化(可以选择从0或者1的下标开始)
        dp[0] = 0; // 起点无消耗
        dp[1] = 0; // 起点无消耗
        // 4.dp 构建
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        // 返回到达顶楼的最低消耗
        return dp[n];

    }

}
