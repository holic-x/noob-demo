package com.noob.algorithm.daily.plan02.day10.p031;

/**
 * 🟡 072 编辑距离 - https://leetcode.cn/problems/edit-distance/description/
 */
public class Solution072_01 {
    /**
     * 思路分析：求将word1转化为word2所使用的最少操作数，可执行3种操作（插入、删除、替换）
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length() + 1, n = word2.length() + 1;
        // 1.dp 定义：dp[i] 表示使得[0,i-1]的word1、[0,j-1]的word2的字符串相同所使用的最少操作数
        int[][] dp = new int[m][n];

        /**
         * 2.dp 递推，根据w1[i-1]与w2[j-1]是否相同来决定是否要执行操作
         * - w1[i-1]==w2[j-1] 无需操作 =>dp[i][j] = dp[i-1][j-1]
         * - w1[i-1]！=w2[j-1] 需执行操作，可从插入、删除、替换当中进行选择
         *      - 删除其中1个元素（删除w1或者删除w2）=> dp[i][j] = min{dp[i-1][j]+1,dp[i][j-1]+1}
         *      - 新增操作，可以理解为等价于上述的删除操作
         *      - 替换操作（在原有的基础上执行1次替换操作） => dp[i][j] = dp[i-1][j-1] + 1
         */

        // 3.dp 初始化
        for (int j = 0; j < n; j++) {
            dp[0][j] = j;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = i;
        }

        // 4.dp 构建
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }
        // 返回结果
        return dp[m - 1][n - 1];
    }
}