package com.noob.algorithm.plan_archive.plan01.day28;

/**
 * 🟢 070 爬楼梯 - https://leetcode.cn/problems/climbing-stairs/description/
 */
public class Solution070_02 {
    /**
     * 动态规划思路(空间优化版本：滚动数组/滚动变量)
     */
    public int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1; // 爬0阶和爬1阶的方案数都是1
        }

        /**
         * 2.dp 递推
         * 爬n阶的方案可以基于爬n-1阶方案基础上再爬1阶或者是爬n-2阶方案再爬2阶得到，因此递推公式有f(n) = f(n-1) + f(n-2)
         */

        // 定义滚动变量，记录递推的中间结果
        int p = 1; // dp[n-2]
        int q = 1; // dp[n-1]
        int r = 0; // dp[n]
        // 递推获取其他
        for (int i = 2; i <= n; i++) {
            r = p + q;
            // 滚动更新变量
            p = q;
            q = r;
        }

        // 返回爬n阶的方案数
        return r;
    }
}
