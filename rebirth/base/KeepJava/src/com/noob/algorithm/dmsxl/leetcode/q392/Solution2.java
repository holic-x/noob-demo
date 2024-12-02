package com.noob.algorithm.dmsxl.leetcode.q392;

/**
 * 392-判断子序列：判断 s 是否为 t 的子序列
 */
public class Solution2 {

    /**
     * 动态规划
     */
    public boolean isSubsequence(String s, String t) {

        // 1.dp定义：dp[i][j]表示以i-1结尾的s，j-1结尾的t
        int[][] dp = new int[s.length() + 1][t.length() + 1];

        /**
         * 2.dp推导：根据dp[i][j]所在位置的s[i-1]与t[j-1]的值比较结果分析
         * s[i-1]==t[j-1]: dp[i][j]=dp[i-1][j-1]+1 (匹配字符，序列+1)
         * s[i-1]!=t[j-1]: dp[i][j]=dp[i][j-1](字符不匹配，删除t[j-1],则取值看s[i-1]和t[j-2]的比较结果)
         */

        // 3.dp初始化（首行首列初始化：结合递推公式判断，初始化为0）

        // 4.dp构建：
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        // 返回结果
        return dp[s.length()][t.length()] == s.length();
    }
}
