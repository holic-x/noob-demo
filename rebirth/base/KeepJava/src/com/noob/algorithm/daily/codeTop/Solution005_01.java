package com.noob.algorithm.daily.codeTop;

/**
 * 🟡 005 最长回文子串 - https://leetcode.cn/problems/longest-palindromic-substring/
 */
public class Solution005_01 {

    /**
     * 思路分析：动态规划
     * boolean dp[i][j] 表示区间[i,j]的字符串是否为回文子串，根据s[i]==s[j]条件是否成立来进行推导
     * - ① s[i]!=s[j] 不可能构成回文子串 => dp[i][j] = false
     * - ② s[i]==s[j] 需分情况讨论
     * - - (1) i==j(类似【a】) => dp[i][j] = true
     * - - (2) j-i==1(类似【aba】) => dp[i][j] = true
     * - - (3) j-i>1(类似【abcba】) => dp[i][j] = dp[i+1][j-1]
     */
    public String longestPalindrome(String s) {
        // 定义最长回文子串长度
        int maxLen = 0;
        String maxStr = ""; // 同步记录最长回文子串长度

        int n = s.length();
        // 1.dp 定义：dp[i][j] 表示区间[i,j]的字符串是否为回文子串
        boolean[][] dp = new boolean[n][n];
        /**
         * 2.dp 递推：校验两个端点的值是否相同
         * s[i]==s[j] 需进一步分情况讨论
         * - i==j(类似【a】) dp[i][j] = true
         * - |i-j|==1(类似【aba】) dp[i][j] = true
         * - |i-j|>1(类似【abcba】) dp[i][j] = dp[i+1][j-1]
         * s[i]!=s[j] 无法构成回文 =>dp[i][j] = false;
         */

        // 3.dp 初始化（默认初始化为false）

        // 4.dp 构建（结合递推公式可知，其遍历顺序为从下往上、从左往右）
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) { // 区间取[i,j]
                if (s.charAt(i) == s.charAt(j)) {
                    if (i == j || j - i == 1) {
                        dp[i][j] = true;
                        if (maxLen < (j - i + 1)) {
                            maxLen = j - i + 1;
                            maxStr = s.substring(i, j + 1); // 同步更新最长回文子串
                        }
                    } else if (j - i > 1) {
                        dp[i][j] = dp[i + 1][j - 1];
                        // 如果为回文子串，则更新maxLen
                        if (dp[i][j]) {
                            if (maxLen < (j - i + 1)) {
                                maxLen = j - i + 1;
                                maxStr = s.substring(i, j + 1); // 同步更新最长回文子串
                            }
                        }
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        // 返回结果
        return maxStr;
    }

}
