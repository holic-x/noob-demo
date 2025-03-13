package com.noob.algorithm.daily.codeTop;

/**
 * 🟡 221 最大正方形 - https://leetcode.cn/problems/maximal-square/description/
 */
public class Solution221_04 {

    // 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积
    public int maximalSquare(char[][] matrix) {
        // 空矩阵处理检查
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        // 非空矩阵处理
        int m = matrix.length, n = matrix[0].length;

        // ① dp 定义(dp[i][j] 表示以(i,j)为右下角的可构成的全为1的正方形边长)
        int[][] dp = new int[m][n];

        /**
         * ② dp 递推
         * matrix[i][j]==0:无法构成全为1的正方形，dp[i][j]=0
         * matrix[i][j]==1:需关注其左侧、左上、上侧三个位置的情况
         */

        // ③ dp 初始化
        dp[0][0] = matrix[0][0] == '1' ? 1 : 0;
        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j] == '1' ? 1 : 0;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
        }

        // ④ dp 构建
        int maxSide = 0;
        // 初始化 maxSide 为第一行和第一列的最大值
        for (int j = 0; j < n; j++) {
            maxSide = Math.max(maxSide, dp[0][j]);
        }
        for (int i = 0; i < m; i++) {
            maxSide = Math.max(maxSide, dp[i][0]);
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                /*
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                } else {
                    // 校验左侧、左上、上侧三个位置的情况
                    if (matrix[i][j - 1] == '0' || matrix[i - 1][j] == '0' || matrix[i - 1][j - 1] == '0') {
                        dp[i][j] = 0;
                    } else {
                        // 错误思路❌ 此处dp[i][j] = dp[i - 1][j - 1] + 1 只考虑到了左上侧，而没有考虑左侧、上侧导致递推公式错误
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                }
                maxSide = Math.max(maxSide, dp[i][j]);
                 */
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                } else {
                    // dp[i][j]受限于左侧、左上、上侧3个位置方向
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
                maxSide = Math.max(maxSide, dp[i][j]);
            }
        }

        // 返回结果
        return maxSide * maxSide;
    }

}
