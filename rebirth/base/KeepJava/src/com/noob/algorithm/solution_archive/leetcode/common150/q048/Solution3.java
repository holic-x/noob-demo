package com.noob.algorithm.solution_archive.leetcode.common150.q048;

/**
 * 048 旋转图像
 */
public class Solution3 {
    /**
     * 原地旋转法：寻找原地旋转规律
     */
    public void rotate(int[][] matrix) {
        // 因为矩阵是正方形的，此处只需取一个长度
        int n = matrix.length;

        /**
         * 基于翻转的思路：水平翻转+对角线翻转（基于翻转思路可以交换元素，而不用像方法2那样绕圈圈考虑元素覆盖规律）
         * 1.水平翻转：matrix[row][col] => matrix[n-row-1]][col] (x翻转，y不变)
         * 2.对角线翻转：matrix[row][col] => matrix[col][row] (x、y对调)
         */
        // 1.进行水平翻转(翻转范围：翻转上面一半的位置)
        for (int i = 0; i < n / 2; i++) { // 限定水平线
            for (int j = 0; j < n; j++) {
                // 翻转元素（交换）
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }

        // 2.进行对角线翻转(翻转范围：主对角线一半的位置)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) { // 限定主对角线
                // 翻转元素（交换）
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
