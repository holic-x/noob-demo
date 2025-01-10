package com.noob.algorithm.daily.plan01.day26;

import com.noob.algorithm.dmsxl.util.PrintUtil;

import java.util.PriorityQueue;

/**
 * 🟢 070 爬楼梯
 */
public class Solution070_01 {


    /**
     * 思路分析：
     * 爬n阶的方案 可以为 爬n-1阶方案的基础上爬1阶 + 爬n-2阶方案的基础上爬2阶 =》 f(n) = f(n-1) + f(n-2)
     */
    public int climbStairs(int n) {
        // 1.dp 构建
        int[] dp = new int[n + 1]; // 爬n阶，需要计算到n，此处扩展数组为n+1

        // 2.dp 递推：f(n) = f(n-1) + f(n-2)

        // 3.dp初始化
        dp[0] = 1;
        dp[1] = 1;

        // 4.构建dp
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        PrintUtil.print(dp);

        // 返回爬n阶方案数
        return dp[n];
    }

    public static void main(String[] args) {
        Solution070_01 solution = new Solution070_01();
        solution.climbStairs(10);
    }

}
