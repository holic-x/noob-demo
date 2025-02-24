package com.noob.algorithm.solution_archive.dmsxl.leetcode.q343;

/**
 * 343 整数拆分
 */
public class Solution1 {

    public int integerBreak(int n) {
        // 特例判断
        if (n == 0 || n == 1) {
            return n;
        }
        // 1.定义dp（dp[i]表示第i个数拆分多个整数之后可构成的最大乘积）
        int[] dp = new int[n + 1]; // 需要计算到n为下标索引的位置，此处扩宽数组到n+1
        /**
         * 2.递推公式推导
         * a.两个数的数学拆分：(i-j)*j (拆分为2个数)
         * b.基于dp的概念拆分：dp[i-j]*j (拆分为多个数)
         * 在推导过程中演变（记录dp[i]的最大值）=》 dp[i] = max{dp[i],max{dp[i-j]*j,(i-j)*j}}
         */

        // 3.dp初始化
        dp[2] = 1; // 1*1=1

        // 4.构建dp
        for (int i = 3; i <= n; i++) { // 外部循环：遍历i用于计算（限制终点）
            for (int j = 2; j <= i; j++) { // 内部循环：遍历j用于拆分整数（控制起点）
                dp[i] = Math.max(dp[i], Math.max(dp[i - j] * j, (i - j) * j));
            }
        }

        // 返回结果
        return dp[n];
    }

}
