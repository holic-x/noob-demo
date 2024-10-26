package com.noob.algorithm.common150.q073;

/**
 * 073 矩阵置0
 */
public class Solution1 {
    /**
     * 标记法：遍历矩阵，将元素为0的数据所在行、列进行标记
     */
    public void setZeroes(int[][] matrix) {
        // 获取矩阵属性
        int m = matrix.length,n = matrix[0].length;
        // 定义两个数组分别存储矩阵元素对应行列的标记
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];

        // 遍历矩阵
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0){
                    row[i]=true;
                    col[j]=true;
                }
            }
        }
        // 遍历标记矩阵
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(row[i]||col[j]){
                    matrix[i][j]=0;
                }
            }
        }
    }
}
