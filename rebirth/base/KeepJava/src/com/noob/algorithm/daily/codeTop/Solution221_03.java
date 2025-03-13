package com.noob.algorithm.daily.codeTop;

/**
 * 🟡 221 最大正方形 - https://leetcode.cn/problems/maximal-square/description/
 */
public class Solution221_03 {

    // 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积
    public int maximalSquare(char[][] matrix) {
        // 空矩阵处理检查
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        // 非空矩阵处理
        int m = matrix.length, n = matrix[0].length;
        // 遍历每一个为1的点，判断基于该点作为左上角可能构成的最大正方形
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    maxArea = Math.max(maxArea, getMaxSquare(matrix, i, j));
                }
            }
        }
        // 返回结果
        return maxArea;
    }

    // 校验最大正方形
    private int getMaxSquare(char[][] matrix, int x, int y) {
        int m = matrix.length, n = matrix[0].length;
        int maxSide = 1; // 维护当前可构成全为1的正方形的最大边长
        // 获取当前点可能构成的最大正方形的区域位置（边长）
        int curMaxSide = Math.min(m - x, n - y); // 根据(x,y)位置确定可能构成的最大正方形的边长
        // 校验(x,y)下方和右侧的1的情况
        for (int k = 0; k < curMaxSide; k++) {
            // 判断新增的一行、一列是否均为1
            boolean flag = true;

            // ----- 判断[0,k]范围内圈出的正方形中的元素是否为1
            for (int i = 0; i <= k; i++) {
                for (int j = 0; j <= k; j++) {
                    if (matrix[x + i][y + j] != '1') {
                        flag = false;
                        break;
                    }
                }
            }

            // 如果满足条件，则说明可以将其纳入新增
            if (flag) {
                maxSide = k + 1; // 更新最大边长
            } else {
                break;
            }
        }
        return maxSide * maxSide;
    }

}
