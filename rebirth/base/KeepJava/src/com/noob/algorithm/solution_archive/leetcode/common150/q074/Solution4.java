package com.noob.algorithm.solution_archive.leetcode.common150.q074;

/**
 * 搜索二维矩阵（74）
 */
public class Solution4 {
    /**
     * 规律法：选择左下角开始作为比较基准base(row,col)，和target进行匹配
     * - 如果base>target: 说明要往上走才能找到更小的值，因此row--
     * - 如果base<target:说明要往右走才能找到更大的值，因此col++
     * - base==target 找到目标值
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        // 初始化从左下角的元素开始比较
        int row = m - 1, col = 0;
        // 当两个指针有一个走到边界则结束
        while (row >= 0 && col <= n - 1) {
            int base = matrix[row][col];
            if (base > target) {
                row--;
            } else if (base < target) {
                col++;
            } else if (base == target) {
                return true;
            }
        }
        // 没找到
        return false;
    }
}
