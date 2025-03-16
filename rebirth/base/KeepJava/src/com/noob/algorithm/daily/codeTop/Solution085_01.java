package com.noob.algorithm.daily.codeTop;

/**
 * 🔴 085 最大矩形 - https://leetcode.cn/problems/maximal-rectangle/description/
 */
public class Solution085_01 {
    /**
     * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积
     */
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int maxArea = 0;
        // 遍历每一个可能的起点（val为1），随后基于其为左上角计算可能构成的矩形（全为1）
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    // 更新全为1的最大矩形面积
                    maxArea = getMaxRectangleArea(matrix, i, j);
                }
            }
        }
        // 返回结果
        return maxArea;
    }

    // 获取基于(x,y)为左上角构建的最大矩形面积
    private int getMaxRectangleArea(char[][] matrix, int x, int y) {
        int maxArea = 0;
        int m = matrix.length, n = matrix[0].length;
        // 校验基于（x,y）点作为左上角可能构建的最大矩形
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    // 初始化当前矩形的最大宽度
                    int maxWidth = n - j;
                    // 遍历所有可能的右下角 (k, l)
                    for (int k = i; k < m; k++) {
                        for (int l = j; l < j + maxWidth; l++) {
                            // 如果遇到 '0'，更新当前矩形的最大宽度
                            if (matrix[k][l] == '0') {
                                maxWidth = l - j;
                                break;
                            }
                        }
                        // 计算当前矩形的面积
                        int area = (k - i + 1) * maxWidth;
                        // 更新最大面积
                        maxArea = Math.max(maxArea, area);
                    }
                }
            }
        }
        return maxArea;
    }
}
