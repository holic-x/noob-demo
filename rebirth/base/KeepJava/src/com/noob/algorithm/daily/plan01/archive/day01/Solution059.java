package com.noob.algorithm.daily.plan01.archive.day01;

/**
 * 🟡 059 螺旋矩阵
 */
public class Solution059 {
    // 思路：4指针+4方向遍历（遍历完1行或1列就缩边）
    public int[][] generateMatrix(int n) {
        // 定义n*n矩阵，存储矩形方阵元素
        int[][] matrix = new int[n][n];

        // 从4个方向遍历矩阵，定义4个边界指针
        int left = 0, right = n - 1, top = 0, bottom = n - 1;

        // 当指针相遇则退出遍历
        int cnt = 1;// 数字计数器
        while (left <= right && top <= bottom) {
            // 1.从左到右方向遍历
            for (int j = left; j <= right; j++) {
                matrix[top][j] = cnt;
                cnt++;
            }
            top++; // 遍历完一行，top指针下移

            // 2.从上到下方向遍历
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = cnt;
                cnt++;
            }
            right--; // 遍历完一列，right指针左移

            // 3.从右往左方向遍历
            if (top <= bottom) { // 存在行才需遍历
                for (int j = right; j >= left; j--) {
                    matrix[bottom][j] = cnt;
                    cnt++;
                }
            }
            bottom--; // 遍历往一行，bottom指针上移

            // 4.从下往上方向遍历
            if (left <= right) { // 存在列才需遍历
                for (int i = bottom; i >= top; i--) {
                    matrix[i][left] = cnt;
                    cnt++;
                }
            }
            left++; // 遍历往一列，left指针右移
        }

        // 返回构建结果
        return matrix;
    }

}
