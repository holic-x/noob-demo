package com.noob.algorithm.dmsxl.leetcode.q583;


import com.noob.algorithm.dmsxl.util.PrintUtil;

/**
 * 583 两个字符串的删除操作
 */
public class Solution1 {

    /**
     * 动态规划：dp 设定存储针对的是"最小删除步数"
     */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        // 1.dp定义（dp[i][j]表示以i-1结尾的word1、j-1结尾的word2 达到相等所需删除的最小步数）
        int[][] dp = new int[len1 + 1][len2 + 1];

        /**
         * 2.dp推导:根据当前校验位置元素是否相等决定是否要执行删除操作
         * [a] word1[i-1]==word2[j-1] : 当前位置元素相等，不需要执行删除操作，继承状态 =》 dp[i][j]=dp[i-1][j-1]
         * [b] word1[i-1]!=word[j-1] : 当前位置元素不相等，需要执行删除操作，有3种删除操作可供选择：
         * - 删除word1：dp[i][j] = dp[i-1][j] + 1
         * - 删除word2：dp[i][j] = dp[i][j-1] + 1
         * - 同时删除word1和word2：dp[i][j] = dp[i-1][j-1] + 2
         * => dp[i][j] = min{dp[i-1][j] + 1,dp[i][j-1] + 1,dp[i-1][j-1] + 2}
         */

        // 3.dp初始化(dp[0][0]、首行、首列)
        // 两者均为空字符串不需要执行删除操作，本身就相等，所需最小步数为0
        dp[0][0] = 0;
        // 当一个word为空，另一个不为空，若要达到相等的状态，需要将不为空的字符串删除至空（所需删除的最小步数为非空字符串的长度）
        for (int i = 1; i <= len1; i++) { // 需注意此处的dp范围，以构建dp为基础，避免漏掉边界情况
            dp[i][0] = i;
        }
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = j;
        }

        // 4.dp构建(根据递推公式分析，需从上往下、从左往右)
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 元素相等，不需要删除
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 元素不相等，有3种情况考虑
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 2, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }

        PrintUtil.printMatrix(dp);

        // 返回结果
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        solution1.minDistance("a", "b");
    }

}
