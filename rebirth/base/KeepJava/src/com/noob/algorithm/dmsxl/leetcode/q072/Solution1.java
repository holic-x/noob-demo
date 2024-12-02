package com.noob.algorithm.dmsxl.leetcode.q072;

import com.noob.algorithm.dmsxl.util.PrintDPUtil;
import com.noob.algorithm.hot100.q015.Solution;

/**
 * 072 编辑距离
 */
public class Solution1 {

    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        // 1.dp定义（dp[i][j]表示以i-1结尾的word1、j-1结尾的最近编辑距离）
        int[][] dp = new int[len1 + 1][len2 + 1];

        /**
         * 2.dp推导
         * word1[i-1]==word2[j-1]: 元素匹配，无需操作，继承上一校验位置的状态 =》 dp[i][j] = dp[i-1][j-1]
         * word1[i-1]!=word2[j-1]: 元素不匹配，分析编辑操作的不同情况（删除、添加、替换）
         * ① 删除：
         * - word1删除一个元素：dp[i][j]=dp[i-1][j]+1
         * - word2删除一个元素：dp[i][j]=dp[i][j-1]+1 (加1表示上一状态的值加上一个操作得到dp[i][j])
         * ② 添加：添加操作和删除操作的操作次数可以看做是等价的，word1删除一个元素的操作可以看做word2添加一个元素，同理word2删除一个元素的操作可以看做word1添加一个元素
         * ③ 替换：替换的目的在于替换1次使得word1[i-1]=word2[j-1],因此dp[i][j]=dp[i-1][j-1]+1 (加1表示上一状态的值加上一个操作得到dp[i][j])
         */

        // 3.dp初始化
        dp[0][0] = 0;
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = j;
        }
        System.out.println("初始化：");
        PrintDPUtil.printMatrix(dp);

        // 4.dp构建
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        System.out.println("构建后：");
        PrintDPUtil.printMatrix(dp);

        // 返回结果
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        Solution1 solution1 = new Solution1();
        solution1.minDistance(word1, word2);
    }
}
