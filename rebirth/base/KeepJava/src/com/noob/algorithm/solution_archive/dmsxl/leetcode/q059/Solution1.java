package com.noob.algorithm.solution_archive.dmsxl.leetcode.q059;

/**
 * 059 螺旋矩阵
 */
public class Solution1 {

    // 思路：4指针+4方向遍历（遍历完1行或1列就缩边）
    public int[][] generateMatrix(int n) {
        // 定义二维矩阵
        int[][] martix = new int[n][n];

        // 循环遍历存储元素
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        int count = 1;
        // 分别从4个方向封装数据
        while (top <= bottom && left <= right) { // 注意循环条件：边界重合

            // 从左到右（row不变，col变）
            for (int i = left; i <= right; i++) {
                martix[top][i] = count;
                count++;
            }
            top++; // 单行遍历完成，top下移

            // 从上到下（row变，col不变）
            for (int i = top; i <= bottom; i++) {
                martix[i][right] = count;
                count++;
            }
            right--; // 单列遍历完成，right左移

            // 从右到左(row不变，col变)
            if (top <= bottom) { // 存在行才需遍历
                for (int i = right; i >= left; i--) {
                    martix[bottom][i] = count;
                    count++;
                }
            }
            bottom--; // 单行遍历完成，bottom上移

            // 从下到上(row变，col不变)
            if (left <= right) { // 存在列才需遍历
                for (int i = bottom; i >= top; i--) {
                    martix[i][left] = count;
                    count++;
                }
            }
            left++; // 单列遍历完成，left右移
        }

        // 返回最终封装的矩阵
        return martix;
    }

    public static void main(String[] args) {
        Solution1 s = new Solution1();
        s.generateMatrix(1);
        System.out.println("1111");
    }
}
