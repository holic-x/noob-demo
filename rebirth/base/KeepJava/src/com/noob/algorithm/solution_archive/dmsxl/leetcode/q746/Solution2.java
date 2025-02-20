package com.noob.algorithm.solution_archive.dmsxl.leetcode.q746;

/**
 * 746 使用最小花费爬楼梯
 */
public class Solution2 {
    // 动态规划：空间优化版本
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;

        int p = 0; // 起点无消耗 dp[i-2]
        int q = 0; // 起点无消耗 dp[i-1]
        int r = 0; // dp[i] 到达第i阶所需要的最低消耗

        for (int i = 2; i <= n; i++) {
            r = Math.min(p + cost[i - 2], q + cost[i - 1]);
            // 滚动变量更新
            p = q;
            q = r;
        }

        // 返回到达顶楼的最低消耗
        return r;
    }
}
