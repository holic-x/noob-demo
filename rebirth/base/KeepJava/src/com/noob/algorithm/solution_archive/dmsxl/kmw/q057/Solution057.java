package com.noob.algorithm.solution_archive.dmsxl.kmw.q057;

/**
 * kmw 057-爬楼梯升级版
 */
public class Solution057 {

    public int climbStairs(int m, int n) {
        // 1.定义dp（dp[j]表示爬j阶楼梯的组合数）
        int[] dp = new int[n + 1];

        /**
         * 2.dp推导：
         * j>=i: dp[j]+=dp[j-i] (累加：爬+不爬的组合之和)
         */

        // 3.初始化
        dp[0] = 1; // 表示爬0阶楼梯有一种方案，就是不爬

        // 4.dp构建(先背包容量后物品 + 正序遍历背包)
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= m; i++) {
                if (j >= i) {
                    dp[j] += dp[j - i];
                }
            }
        }
        // 返回爬楼梯组合数
        return dp[n];
    }

    public static void main(String[] args) {
        int m = 2, n = 3;
        Solution057 solution057 = new Solution057();
        System.out.println(solution057.climbStairs(m, n));
    }
}
