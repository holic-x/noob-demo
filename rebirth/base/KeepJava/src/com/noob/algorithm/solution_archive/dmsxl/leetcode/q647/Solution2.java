package com.noob.algorithm.solution_archive.dmsxl.leetcode.q647;

import java.util.Arrays;

/**
 * 647 回文子串
 */
public class Solution2 {
    // 动态规划
    public int countSubstrings(String s) {
        int cnt = 0;

        int len = s.length();

        // 1.dp定义（dp[i][j]表示区间[i,j]的字符串是否否为回文字符串）
        boolean[][] dp = new boolean[len][len];

        /**
         * 2.dp推导：根据s[i]与s[j]的值进行比较
         * s[i]!=s[j]:肯定为非回文 =》 dp[i][j]=false
         * s[i]==s[j]:需考虑3种情况进行分析
         * ①：i==j(a)            => dp[i][j]=true
         * ②：i与j相差1(aba)      => dp[i][j]=true
         * ③：i与j相差大于1(abcba) => dp[i][j]=dp[i+1][j-1]
         */

        // 3.dp初始化(默认初始化为false)

        // 4.dp构建(从下往上，从左往右)
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) { // 区间[i,j]
                if (s.charAt(i) == s.charAt(j)) {
                    if (i == j || j - i == 1) { // 情况①②
                        dp[i][j] = true;
                        cnt++; // 回文计数+1
                    } else if (j - i > 1) {
                        dp[i][j] = dp[i + 1][j - 1]; // 情况③
                        cnt = dp[i][j] ? cnt + 1 : cnt; // 判断回文计数+1
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }

        // 返回结果
        return cnt;
    }

}
