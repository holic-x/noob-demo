package com.noob.algorithm.plan_archive.plan01.day26;

/**
 * 🟢 070 爬楼梯
 */
public class Solution070_02 {

    /**
     * 思路分析：动态规划（空间优化版本）
     * 爬n阶的方案 可以为 爬n-1阶方案的基础上爬1阶 + 爬n-2阶方案的基础上爬2阶 =》 f(n) = f(n-1) + f(n-2)
     */
    public int climbStairs(int n) {

        // 特例判断
        if (n == 0 || n == 1) {
            return 1;
        }

        int p = 1; // dp[i-2]
        int q = 1; // dp[i-1]
        int r = 0;
        // 遍历元素，递推处理
        for (int i = 2; i <= n; i++) {
            r = p + q;
            // 滚动更新变量
            p = q;
            q = r;
        }

        // 返回爬n阶方案数
        return r;
    }

    public static void main(String[] args) {
        Solution070_02 solution = new Solution070_02();
        solution.climbStairs(10);
    }

}
