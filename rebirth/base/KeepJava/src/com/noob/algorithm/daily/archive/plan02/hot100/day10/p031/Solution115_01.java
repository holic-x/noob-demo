package com.noob.algorithm.daily.archive.plan02.hot100.day10.p031;

/**
 * 🔴 115 不同的子序列 - https://leetcode.cn/problems/distinct-subsequences/description/
 */
public class Solution115_01 {

    /**
     * 思路分析：返回s的子序列在t中出现的个数
     * 基于遍历的思路，根据t[0]寻找校验的起点，然后基于此起点校验s是否存在子序列等于t字符串 (❌复杂化)
     * 基于动态规划思路
     */
    public int numDistinct(String s, String t) {

        int m = s.length() + 1, n = t.length() + 1;
        // 1.dp 定义：dp[i][j] 表示基于[0,i-1]的s、[0,j-1]的t，s的子序列在t中出现的个数
        int[][] dp = new int[m][n];

        /**
         * 2.dp 递推（基于s[i-1]与t[j-1]是否相等分情况讨论）
         * - s[i-1]==t[j-1]
         *      - 用s[i-1]来匹配（使用该字符）：dp[i][j]=dp[i-1][j-1]
         *      - 不用s[i-1]匹配（使用该字符）：dp[i][j]=dp[i-1][j]
         * - s[i-1]!=t[j-1]（无法使用该字符匹配）
         */

        // 3.dp 初始化
        dp[0][0] = 1; // 空字符串s可通过删除0个字符得到空字符串t
        // 首行初始化
        for (int j = 1; j < n; j++) {
            dp[0][j] = 0; // 空字符串s无法通过删除操作得到非空字符串t
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = 1; // 非空字符串s可以通过删除操作得到空字符串t（可以理解为空字符串为任意字符串的子序列）
        }

        // 4.dp 构建
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[m-1][n-1];
    }

}
