package com.noob.algorithm.daily.archive.plan01.day24;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟡 054 螺旋矩阵
 */
public class Solution054_01 {

    /**
     * 思路：分别定义4个边界，每遍历1行或1列就进行"缩圈"
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        // 定义结果集
        List<Integer> res = new ArrayList<>();

        // 记录矩阵属性
        int m = matrix.length, n = matrix[0].length;
        // 初始化4个边界
        int left = 0, right = n - 1, top = 0, bottom = m - 1;

        // 当两个边界均相遇则停止遍历
        while (left <= right && top <= bottom) {
            // 依次按照4个方向进行遍历

            // ① 从左到右
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            top++;

            // ② 从上到下
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            right--;

            // ③ 从右到左
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    res.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // ④ 从下到上
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    res.add(matrix[i][left]);
                }
                left++;
            }
        }

        // 返回封装结果
        return res;

    }

}
