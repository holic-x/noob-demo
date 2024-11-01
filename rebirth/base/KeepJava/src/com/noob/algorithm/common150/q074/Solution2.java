package com.noob.algorithm.common150.q074;

/**
 * 搜索二维矩阵（74）
 */
public class Solution2 {
    /**
     * 二分法：矩阵元素按行或者按列是递增的，因此可以先定位行，再检索行
     * todo 需处理数据边界问题 乱七八糟
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int top = 0, bottom = n - 1;

        // 如果只有1行，直接按行检索
        if (m == 1) {
            return searchByRow(matrix[0], target);
        }

        // 如果超出1行则先定位到其在哪一行，然后再按行检索
        while (top <= bottom) {
            int mid = (top + bottom) / 2;
            // 判断target和matrix[mid][0]的大小进行缩圈
            if (target == matrix[mid][0]) {
                return true;
            }
            if (target < matrix[mid][0]) {
                // target在上半部分
                bottom = mid - 1;
            } else if (target > matrix[mid][0]) {
                // target在下半部分
                top = mid + 1;
            }
        }
        // 如果执行到此处则选择top-1的位置，对应到行
        if(top==0){
            return searchByRow(matrix[0],target);
        }else{
            return searchByRow(matrix[top-1], target);
        }

    }

    private boolean searchByRow(int[] row, int target) {
        int left = 0, right = row.length - 1;
        // 如果继续走到此处，拿到top，表示定位到target对应行
        while (left <= right) {
            int mid = (left + right) / 2;
            // 判断target和matrix[top][mid]的大小进行缩圈
            if (target == row[mid]) {
                return true;
            }
            if (target < row[mid]) {
                // target在左半部分
                right = mid - 1;
            } else if (target > row[mid]) {
                // target在右半部分
                left = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1}, {3}};
        Solution2 solution2 = new Solution2();
        solution2.searchMatrix(matrix, 0);
    }
}
