package com.noob.algorithm.daily.plan03.hot100_daily.day10.p031;

/**
 * 🔴 115 不同的子序列 - https://leetcode.cn/problems/distinct-subsequences/description/
 */
public class Solution115_01 {

    /**
     * 思路分析：
     * 给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数
     */
    public int numDistinct(String s, String t) {
        // dp[i][j] 表示以i-1位置结尾的s的子序列在 j-1位置结尾的t中出现的个数
        int m = s.length() + 1, n = t.length() + 1;

        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        // dp[0][j]
        for (int j = 0; j < n; j++) {
            dp[0][j] = 0; // 空字符串s无法构成t
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1; // 字符串s可以通过任意删除元素构成空字符串t，得到1个满足的结果
        }

        // dp 构建
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    // s[i-1] = t[j-1] 字符匹配(可选择是否需要用i-1这个位置的字符)
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];

                } else {
                    // s[i-1] != t[j-1] 字符不匹配，只能删掉
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // 返回结果
        return dp[m - 1][n - 1];
    }

}
