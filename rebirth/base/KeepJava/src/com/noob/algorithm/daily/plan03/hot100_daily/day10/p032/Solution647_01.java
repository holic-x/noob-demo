package com.noob.algorithm.daily.plan03.hot100_daily.day10.p032;

import java.util.Arrays;

/**
 * 🟡 647 回文子串（统计回文字串的数目） - https://leetcode.cn/problems/palindromic-substrings/
 */
public class Solution647_01 {

    /**
     * 思路分析：
     */
    public int countSubstrings(String s) {
        // boolean[i][j] 表示[i,j]范围内的字符串s是否为回文子串
        int n = s.length();

        boolean[][] dp = new boolean[n][n];
        // 元素自身均为一个回文子串 但此处初始化为false作为后续的推导基础
        /*
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], true);
        }
         */

        /**
         * 回文递推：
         * [i,i+1....j-1,j]
         * - s[i]==s[j] 需结合i，j的相对位置分析
         * - - ① i==j 指向同一个数，dp[i][j] = true
         * - - ② |i-j|=1 相差一位且相同，dp[i][j] = true
         * - - ③ |i-j|>1 相差不止一位，但相同，则取决于更细的范围 dp[i][j] = dp[i+1][j-1]
         * - s[i]!=s[j] , (i,j) 不可能构成回文子串
         */
        int cnt = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (i == j) {
                        dp[i][j] = true;
                    } else if (j - i == 1) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }

                // 回文子串统计
                if (dp[i][j]) {
                    cnt++;
                }
            }
        }
        // 返回统计结果
        return cnt;
    }


}
