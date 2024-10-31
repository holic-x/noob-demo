package com.noob.algorithm.common150.q048;

/**
 * 048 旋转图像
 */
public class Solution2 {
    /**
     * 原地旋转法：寻找原地旋转规律
     */
    public void rotate(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        // 遍历矩阵元素进行原地旋转
        for (int row = 0; row < m / 2; row++) {
            for (int col = 0; col < (n + 1) / 2; col++) {
                /**
                 * 旋转规律算法：核心等式（row=col，col=n-row-1）,依次分析每个元素旋转后会覆盖到哪个位置
                 * 【1】matrix[row][col]
                 * 【2】matrix[col][n-row-1]
                 * 【3】matrix[n-row-1][n-col-1]
                 * 【4】matrix[n-col-1][row]
                 * 【5】matrix[row][col]
                 * 可以看到元素的一个旋转路线，则可通过临时变量temp存储，然后从下往上依次覆盖
                 */
                int temp = matrix[row][col];
                matrix[row][col] = matrix[n - col - 1][row];
                matrix[n - col - 1][row] = matrix[n - row - 1][n - col - 1];
                matrix[n - row - 1][n - col - 1] = matrix[col][n - row - 1];
                matrix[col][n - row - 1] = temp;
            }
        }

    }
}
