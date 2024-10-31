package com.noob.algorithm.common150.q054;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 */
public class Solution1 {
    /**
     * 螺旋矩阵：顺时针螺旋顺序，遍历矩阵元素
     * 通过4个指针进行控制（设定边界），到达边界则切换方向并进行缩边
     */
    public List<Integer> spiralOrder(int[][] matrix) {

        // 定义返回结果，封装遍历元素
        List<Integer> list = new ArrayList<>();

        // 获取矩阵属性
        int m = matrix.length;
        int n = matrix[0].length;

        // 定义4个指针边界
        int left = 0, right = n - 1, top = 0, bottom = m - 1;

        // 按照指定方向次序遍历元素(指针相遇则遍历结束)
        while (left <= right && top <= bottom) {
            // 从左往右遍历
            for (int i = left; i <= right; i++) {
                list.add(matrix[top][i]);
            }
            top++; // top 缩边

            // 从上往下遍历
            for (int i = top; i <= bottom; i++) {
                list.add(matrix[i][right]);
            }
            right--; // right 缩边

            // 从右往左遍历
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    list.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // 从下往上遍历
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    list.add(matrix[i][left]);
                }
                left++;
            }
        }

        return list;
    }
}
