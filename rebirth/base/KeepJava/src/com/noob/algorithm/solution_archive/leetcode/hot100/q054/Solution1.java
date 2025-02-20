package com.noob.algorithm.solution_archive.leetcode.hot100.q054;

import java.util.ArrayList;
import java.util.List;

/**
 * 054 螺旋矩阵
 */
public class Solution1 {

    /**
     * 边界判断：
     * 定义上下左右4个指针边界，然后按照一定方向来遍历元素，当指针相遇（边界重合则循环结束）
     * 1.从左往右：当遇到右边界，则向下
     */
    public List<Integer> spiralOrder(int[][] matrix) {

        // 定义响应结果
        List<Integer> list = new ArrayList<Integer>();

        // m*n矩阵
        int m = matrix.length - 1, n = matrix[0].length - 1;

        // 定义指针边界
        int left = 0; // 左指针
        int right = n; // 右指针
        int top = 0; // 上指针
        int bottom = m; // 下指针

        // 指针相遇循环结束否则继续遍历
        while (left <= right && top <= bottom) {

            // 从左往右(行不变，列变)
            for (int i = left; i <= right; i++) {
                list.add(matrix[top][i]);
            }
            // 当前方向遍历完成，top指针下移
            top++;

            // 从上往下遍历（行变，列不变）
            for (int i = top; i < bottom; i++) {
                list.add(matrix[i][right]);
            }
            // 当前方向遍历完成，右指针左移
            right--;

            // 从右往左遍历（行不变，列变） 当上下边界没有越界（避免重复读取）
            if(top <= bottom) {
                for (int i = right; i >= left; i--) {
                    list.add(matrix[bottom][i]);
                }
            }
            // 当前方向遍历完成，下指针上移
            bottom--;

            // 从下往上遍历（行变，列不变） 当左右边界没有越界（避免重复读取）
            if(left <= right) {
                for (int i = bottom; i >= top; i--) {
                    list.add(matrix[i][left]);
                }
            }
            // 当前方向遍历完成，左指针右移
            left++;

        }

        // 返回结果
        return list;
    }
}
