package com.noob.algorithm.daily.plan03.hot100_daily.day08.p026;

/**
 * 🟡 1049 - 最后一块石头的重量II - https://leetcode.cn/problems/last-stone-weight-ii/
 */
public class Solution1049_01 {

    /**
     * 思路分析：
     * 最理想的情况：尽量让石头分成重量相同的两堆，相撞之后剩下的石头最小，以此化解成01背包问题
     * sum - 2*dp[m-1][bagSize]
     */
    public int lastStoneWeightII(int[] stones) {

        // 计算sum
        int sum = 0;
        for (int num : stones) {
            sum += num;
        }
        int bagSize = sum / 2;

        // 动态规划
        int m = stones.length, n = bagSize + 1;
        int[][] dp = new int[m][n]; // 表示在[0,m)中的物品中选择填充容量为n的背包可获得的最大价值

        // 初始化
        dp[0][0] = 0;
        // 首行初始化（装填物品0）
        for (int j = 1; j < n; j++) {
            dp[0][j] = (j >= stones[0]) ? stones[0] : 0;
        }
        // 首列初始化（容量为0）
        for (int i = 1; i < m; i++) {
            dp[i][0] = 0; // 装不了任何东西
        }

        // dp构建
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 判断当前容量是否可装入物品
                if (j >= stones[i]) {
                    // 可选择装或者不装
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i]] + stones[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // 返回结果
        return sum - 2 * dp[m - 1][n - 1];
    }
}
