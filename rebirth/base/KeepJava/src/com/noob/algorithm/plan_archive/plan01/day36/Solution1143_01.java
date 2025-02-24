package com.noob.algorithm.plan_archive.plan01.day36;

/**
 * 🟡 1143 最长公共子序列 - https://leetcode.cn/problems/longest-common-subsequence/description/
 */
public class Solution1143_01 {

    /**
     * 动态规划：铺成二维数组进行一一对比分析
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length() + 1, n = text2.length() + 1;

        // 1.dp 定义：dp[i][j] 表示[0,i-1]范围的text1 [0,j-1]的text2 的最长公共子序列长度
        int[][] dp = new int[m][n];

        /**
         * 2.dp 递推：
         * dp[i][j] 可以由左侧、左上、上侧三个方向推导出来，根据当前比较位置元素是否相等来区分不同的情况
         * text1[i-1]==text2[j-1]: dp[i][j] = dp[i-1][j-1] + 1 (累加)
         * text1[i-1]!=text2[j-1]: dp[i][j] = max{dp[i][j-1],dp[i-1][j]} 从其左侧、上侧推导选择max
         */

        // 3.初始化（首行、首列初始化）
        for (int j = 0; j < n; j++) {
            dp[0][j] = 0;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = 0;
        }

        // 4.dp 构建
        int maxLen = -1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }

        // 返回结果
//        return maxLen;
        return dp[m - 1][n - 1];
    }

}
