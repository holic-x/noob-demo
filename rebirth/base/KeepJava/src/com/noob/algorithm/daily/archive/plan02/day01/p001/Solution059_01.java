package com.noob.algorithm.daily.archive.plan02.day01.p001;


/**
 * 🟡 059 螺旋矩阵II - https://leetcode.cn/problems/spiral-matrix-ii/
 */
public class Solution059_01 {
    /**
     * 生成[1,n*n]按照顺时针顺序螺旋排列的正方形矩阵
     */
    public int[][] generateMatrix(int n) {
        // 定义二维矩阵
        int[][] matrix = new int[n][n];
        int cur = 1;
        // 定义四边界指针
        int left = 0, right = n - 1, top = 0, bottom = n - 1;
        while (left <= right && top <= bottom) {
            // 从左到右（遍历top行）
            for (int j = left; j <= right; j++) {
                matrix[top][j] = cur;
                cur++;
            }
            top++; // 单行遍历完成，top下移

            // 从上到下（遍历right列）
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = cur;
                cur++;
            }
            right--; // 单列遍历完成，right左移

            // 从右到左（遍历bottom行）
            if (top <= bottom) { // 存在行才需要遍历
                for (int j = right; j >= left; j--) {
                    matrix[bottom][j] = cur;
                    cur++;
                }
                bottom--; // 单行遍历完成，bottom上移
            }

            // 从下到上（遍历left列）
            if (left <= right) { // 存在列才需要遍历
                for (int i = bottom; i >= top; i--) {
                    matrix[i][left] = cur;
                    cur++;
                }
                left++; // 单列遍历完成，left右移
            }
        }
        // 返回生成的矩阵结果
        return matrix;
    }
}
