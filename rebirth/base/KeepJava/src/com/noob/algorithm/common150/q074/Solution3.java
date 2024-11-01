package com.noob.algorithm.common150.q074;

/**
 * 搜索二维矩阵（74）
 */
public class Solution3 {
    /**
     * 二分法：矩阵元素按行或者按列是递增的，因此可以先定位行，再检索行
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // 先定位target位于哪一行
        int rowIdx = searchByColumn(matrix, target);
        if (rowIdx < 0) {
            return false; // 记录不在指定行
        }
        return searchByRow(matrix[rowIdx], target);
    }

    // 检索目标在哪一行（根据第1列进行检索）
    private int searchByColumn(int[][] matrix, int target) {
        int low = -1, high = matrix.length - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (matrix[mid][0] <= target) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    // 检索目标在哪一行
    private boolean searchByRow(int[] row, int target) {
        int left = 0, right = row.length - 1;
        // 如果继续走到此处，拿到top，表示定位到target对应行
        while (left <= right) {
            // int mid = (left + right) / 2;
            int mid = left + (right - left) / 2;
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
//        int[][] matrix = new int[][]{{1}, {3}};
        int[][] matrix = new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        Solution3 solution3 = new Solution3();
        System.out.println(solution3.searchMatrix(matrix, 3));


        int[] row = new int[]{1, 3, 5, 7};
        System.out.println(solution3.searchByRow(row, 3));

    }
}
