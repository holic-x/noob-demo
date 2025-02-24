package com.noob.algorithm.plan_archive.plan02.hot100.day10.p032;

/**
 * 🟡 647 回文字串（统计回文字串的数目）
 */
public class Solution647_02 {

    /**
     * 思路分析：动态规划思路
     */
    public int countSubstrings(String s) {
        int n = s.length();
        // 1.dp[i][j] 表示[i,j]范围内的字符串是否为回文字符串
        boolean[][] dp = new boolean[n][n];

        /**
         * 2.dp 递推：先校验i、j指向位置字符是否一致进行分类讨论，然后判断i、j间隔进一步确定回文
         * - s[i]!=s[j] 不可能为回文 =>dp[i][j] = false
         * - s[i]==s[j] 根据i、j的间隔来校验
         *      - i==j（例如 a）：dp[i][j] = true
         *      - j-i=1（例如 aba）：dp[i][j] = true
         *      - j-i>1（例如 abcba）：dp[i][j] = dp[i+1][j-1]
         */

        // 3.dp 初始化
        dp[0][0] = true;

        // 4.dp 构建
        int cnt = 0;
        char[] ch = s.toCharArray();
        // 遍历顺序：从下往上 从左往右
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                // ① 根据ch[i] ch[j]两者值比较分情况讨论
                if (ch[i] != ch[j]) {
                    dp[i][j] = false;
                } else {
                    // ② 根据i j的间隔位置进一步分类
                    if (i == j || j - i == 1) {
                        dp[i][j] = true;
                        cnt++; // 判断回文计数+1
                    } else if (j - i > 1) {
                        dp[i][j] = dp[i + 1][j - 1];
                        cnt = dp[i][j] ? cnt + 1 : cnt; // 判断回文计数+1
                    }
                }
            }
        }
        // 返回统计结果
        return cnt;
    }

}
