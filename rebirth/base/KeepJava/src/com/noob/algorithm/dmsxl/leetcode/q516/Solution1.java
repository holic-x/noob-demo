package com.noob.algorithm.dmsxl.leetcode.q516;

import com.noob.algorithm.dmsxl.util.PrintDPUtil;

/**
 * 516 最长回文子序列
 */
public class Solution1 {

    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        // 1.dp 定义 (字符串s在[i, j]范围内最长的回文子序列的长度为dp[i][j])
        int[][] dp = new int[len][len];
        /**
         * 2.dp 递推
         * s[i]!=s[j]: dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]); (s[i]、s[j]的同时加入并不能增加回文子序列的长度，因此看加入s[i]、s[j]哪一个可以组成最长的回文子序列)
         * s[i]==s[j]: dp[i][j] = dp[i + 1][j - 1] + 2
         */

        // 3.dp 初始化（当`i==j`的时候，回文子序列为元素本身，此时`dp[i]][j]=1`，其余情况初始化为0）
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;//i==j时元素自身为一个回文子序列
        }
        System.out.println("初始化：");
        PrintDPUtil.printMatrix(dp);

        // 4.dp 构建（从下往上，从左往右）
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println("构建后：");
        PrintDPUtil.printMatrix(dp);

        // 返回结果(遍历顺序是从下往上，从左往右，因此第一行的最后一个元素为结果)
        return dp[0][len - 1];
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        solution1.longestPalindromeSubseq("cbbd");
    }
}
