package com.noob.algorithm.solution_archive.leetcode.common150.q074;

/**
 * 搜索二维矩阵（74）
 */
public class Solution1 {
    /**
     * 判断元素是否存在于矩阵中
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m ; i++) {
            for (int j = 0; j < n ; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        /*
         for (int[] ints : matrix) {
            for (int anInt : ints) {
                if (anInt == target) return true;
            }
        }
         */
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1}};
        Solution1 solution1 = new Solution1();
        solution1.searchMatrix(matrix,1);
    }
}
