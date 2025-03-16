package com.noob.algorithm.daily.codeTop;

/**
 * 🟡 221 最大正方形 - https://leetcode.cn/problems/maximal-square/description/
 */
public class Solution221_05 {

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

        // ③ dp 初始化（dp、maxSide的初始化在后面的递归过程中处理体现）
        int maxSide = 0;

        // ④ dp 构建
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') { // matrix[i][j] == '0'默认为0，此处无需处理，只考虑`1`的情况
                    // 边界处理
                    if (i == 0 || j == 0) {
                        // 第一行或第一列，dp[i][j] 只能是 1
                        dp[i][j] = 1;
                    } else {
                        // 如果当前位置为1，则受限与 左侧、上侧和左上侧的最小值 + 1
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        // 返回结果
        return maxSide * maxSide;
    }

}
