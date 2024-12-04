package com.noob.algorithm.dmsxl.leetcode.q583;

import com.noob.algorithm.dmsxl.util.PrintUtil;

/**
 * 583 两个字符串的删除操作
 */
public class Solution2 {

    /**
     * 动态规划：dp 设定存储针对的是"最长公共子序列"
     */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        // 1.dp定义（dp[i][j]表示以i-1结尾的word1、j-1结尾的word2 最长公共子序列的长度）
        int[][] dp = new int[len1 + 1][len2 + 1];

        /**
         * 2.dp推导:根据当前校验位置元素是否相等进行情况讨论
         * [a] word1[i-1]==word2[j-1] : 当前位置元素相等，元素匹配则公共子序列长度+1 =》 dp[i][j]=dp[i-1][j-1] + 1
         * [b] word1[i-1]!=word[j-1] : 当前位置元素不相等，相当于将【相等累加的情况】向下、左右传递下去 => dp[i][j] = max{dp[i-1][j] ,dp[i][j-1]}
         */

        // 3.dp初始化(dp[0][0]、首行、首列)
        // 两者均为空字符串，最长公共子序列也为空字符串，长度为0
        dp[0][0] = 0;
        // 两个字符串中其中一个为空字符串，最长公共子序列也为空字符串，长度为0
        for (int i = 1; i <= len1; i++) { // 需注意此处的dp范围，以构建dp为基础，避免漏掉边界情况
            dp[i][0] = 0;
        }
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = 0;
        }

        // 4.dp构建(根据递推公式分析，需从上往下、从左往右)
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 元素相等，累加
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 元素不相等, 相当于将【相等累加的情况】向下、左右传递下去
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        PrintUtil.printMatrix(dp);

        // 返回结果
        return len1 + len2 - 2 * dp[len1][len2];
    }

    public static void main(String[] args) {
        Solution2 solution1 = new Solution2();
        solution1.minDistance("a", "b");
    }

}
