package com.noob.algorithm.solution_archive.dmsxl.leetcode.q1143;

/**
 * 1143 最长公共子序列
 */
public class Solution1 {

    public int longestCommonSubsequence(String text1, String text2) {
        int n1 = text1.length(), n2 = text2.length();
        // 1.dp定义（dp[i][j]表示text1[0,i-1]、text2[0-j-1]的最长公共子序列（不连续））
        int[][] dp = new int[n1 + 1][n2 + 1]; // 冗余首行首列空间，简化初始化和边界校验

        /**
         * 2.dp推导: dp[i][j]位置的处理 与 text1[i-1]、text[j-1]的位置是一一对应的（结合二维矩阵理解），当要处理dp[i][j]的时候，比较的就是 text1[i-1]、text[j-1]
         *  dp[i][j]的推导有两种情况：
         *  当text1[i-1]==text[j-1]（当前位置元素相等）：dp[i][j]=dp[i-1][j-1]+1 (这种情况和【连续重复子数组】概念类似，累加)
         *  当text1[i-1]!=text[j-1]（当前位置元素不相等）dp[i][j]=max{左侧，上方}=max{dp[i][j-1],dp[i-1][j]}(相当于对于不连续的情况，将累加的数值从两个方向传递下去)
         */

        // 3.dp初始化（同理，对于同行同列的初始化，[-1]是无意义的，初始化为0即可）

        // 4.dp构建（对于dp而言从1开始遍历，对于text1、text2是从0开始的）
        for (int i = 1; i <= n1; i++) { // 此处以构建dp为维度(i < n1 + 1)，也可以以遍历text为维度(i<=n1) 于j处理而言同理
            for (int j = 1; j <= n2; j++) { // j< n2+1
                // 判断当前位置元素是否相等
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        // 返回结果
        return dp[n1][n2];
    }
}
