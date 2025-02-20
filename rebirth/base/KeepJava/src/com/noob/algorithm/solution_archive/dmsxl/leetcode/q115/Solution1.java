package com.noob.algorithm.solution_archive.dmsxl.leetcode.q115;

/**
 * 115 不同的子序列
 */
public class Solution1 {

    /**
     * s 的子序列 在 t 中出现的个数
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        // 1.dp 定义（dp[i][j]表示以`i-1`结尾的s的子序列在以`j-1`结尾的t中出现的个数）
        int[][] dp = new int[sLen + 1][tLen + 1];

        /**
         * 2.dp 推导
         * 以s[i-1]是否匹配进行讨论（此处是校验s当中有多少个t）
         * s[i-1]==t[j-1]:分两种情况讨论，即自由选择匹配和不匹配的情况分析 =》dp[i][j] = dp[i-1][j-1](选择匹配) + dp[i-1][j](选择不匹配)
         * s[i-1]!=t[j-1]:只有一种不匹配的情况 =》dp[i][j] = dp[i-1][j]
         */

        // 3.dp 初始化(dp[0][0]、dp[0][j]首行、dp[i][j]首列)
        dp[0][0] = 1; // 空字符串s可以删除任意元素，其在空字符串t中出现的个数为1
        // dp[0][j] 空字符串s可以删除任意元素，其出现`j-1`结尾的字符串t的个数为0（空字符串无法构成t）
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = 0;
        }
        // dp[i][0] 以`i-1`结尾的字符串s可以删除任意元素，其出现空字符串t的个数为1
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = 1;
        }

        // 4.dp 构建
        for (int i = 1; i < dp.length; i++) { // i<=sLen
            for (int j = 1; j < dp[0].length; j++) { // j<=tLen
                // 根据s[i-1]是否匹配分析
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // 返回结果
        return dp[sLen][tLen];
    }

}
