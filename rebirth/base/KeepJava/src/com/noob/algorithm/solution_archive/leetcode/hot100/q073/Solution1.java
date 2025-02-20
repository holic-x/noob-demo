package com.noob.algorithm.solution_archive.leetcode.hot100.q073;

/**
 * 73 矩阵置0
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法
 */
public class Solution1 {
    /**
     * 标记法：
     * 依次遍历元素，如果元素为0，则标记对应行列
     */
    public void setZeroes(int[][] matrix) {

        // 定义行、列用作标记
        boolean row[] = new boolean[matrix.length];
        boolean col[] = new boolean[matrix[0].length];

        // 遍历二维数组元素，标记对应行列
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == 0) {
                    // 如果当前元素为0，标记对应的行、列
                    row[i] = col[j] = true;
                }
            }
        }

        // 再次遍历二维数组元素，根据标记进行置0
        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix[i].length;j++) {
                if(row[i] || col[j]) { // 如果当前元素对应行或者对应列被标记为0，则置0
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
