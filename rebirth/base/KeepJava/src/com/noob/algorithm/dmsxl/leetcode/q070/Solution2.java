package com.noob.algorithm.dmsxl.leetcode.q070;

/**
 * 070-爬楼梯
 */
public class Solution2 {

    // 动态规划:空间优化版本
    public int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int p = 1; // 对应dp[i-2]
        int q = 1; // 对应dp[i-1]
        int r = 0; // 对应dp[i]
        for (int i = 2; i <= n; i++) {
            r = p + q; // 处理dp[i]
            // 变量滚动
            p = q;
            q = r;
        }
        // 返回结果
        return r;
    }

}
