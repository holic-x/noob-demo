package com.noob.algorithm.daily.plan03.hot100_daily.day01.p001;


/**
 * 🟡 059 螺旋矩阵II - https://leetcode.cn/problems/spiral-matrix-ii/
 */
public class Solution059_01 {
    /**
     * 思路分析：定义4个方向的指针，分别向4个方向进行遍历并计数
     */
    public int[][] generateMatrix(int n) {
        // 定义目标矩阵
        int[][] matrix = new int[n][n];

        // 定义4个方向的指针边界
        int left = 0, right = n - 1, top = 0, bottom = n - 1;

        // 当指针处于有效区域内则进行4个方向的遍历
        int cnt = 1;
        while (left <= right && top <= bottom) {
            // ① 从左往右
            for (int col = left; col <= right; col++) {
                matrix[top][col] = cnt++;
            }
            top++;

            // ② 从上往下
            for (int row = top; row <= bottom; row++) {
                matrix[row][right] = cnt++;
            }
            right--;

            // ③ 从右往左
            if (left <= right) {
                for (int col = right; col >= left; col--) {
                    matrix[bottom][col] = cnt++;
                }
                bottom--;
            }

            // ④ 从下往上
            if (top <= bottom) {
                for (int row = bottom; row >= top; row--) {
                    matrix[row][left] = cnt++;
                }
                left++;
            }
        }
        // 返回构建的矩阵结果
        return matrix;
    }
}
