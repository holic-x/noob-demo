package com.noob.algorithm.common150.q048;

/**
 * 048 旋转图像
 */
public class Solution1 {
    /**
     * 借助辅助矩阵存储旋转后的元素
     * - matrix[row][col]旋转后对应位置为matrix[col][n-row-1]
     * - 旋转矩阵规格都是n*n
     */
    public void rotate(int[][] matrix) {
        int m=matrix.length,n=matrix[0].length;
        int[][] temp = new int[m][n];
        // 遍历矩阵元素，将元素与旋转后的位置对照
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                temp[j][n-i-1] = matrix[i][j];
            }
        }

        // 再次遍历旋转后的矩阵元素，覆盖对应位置
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                matrix[i][j] = temp[i][j];
            }
        }
    }
}
