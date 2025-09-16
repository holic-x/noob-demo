package com.noob.algorithm.daily.plan03.hot100_daily.day10.p030;

/**
 * 🟡 1143 最长公共子序列（不连续） - https://leetcode.cn/problems/longest-common-subsequence/description/
 */
public class Solution1143_01 {

    /**
     * 思路分析：
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length() + 1, n = text2.length() + 1;

        // dp[i][j] 表示[0,i-1]范围的text1和[0,j-1]范围的text2的最长公共子序列长度
        int[][] dp = new int[m][n];

        // dp 初始化：无前置推导条件，此处首行首列都初始化为0

        // dp 递推
        int maxVal = 0;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 可衔接的位置是：、左上
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 状态继承：左侧 上侧
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                maxVal = Math.max(dp[i][j], maxVal);
            }
        }

        // 返回结果
        return maxVal;
    }
}
