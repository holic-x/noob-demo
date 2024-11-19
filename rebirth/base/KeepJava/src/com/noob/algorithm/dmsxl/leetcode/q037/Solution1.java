package com.noob.algorithm.dmsxl.leetcode.q037;

import java.util.Arrays;

/**
 * 037 解数独
 */
public class Solution1 {

    public void solveSudoku(char[][] board) {
        // 调用回溯算法
        backTrack(board);
    }


    // 定义回溯算法
    public boolean backTrack(char[][] board) {

        // 双层循环放置元素，确定某个位置存放1-9是否合适，合适则递归存放下一个位置
        for (int i = 0; i < board.length; i++) { // 遍历行
            for (int j = 0; j < board[0].length; j++) { // 遍历列
                // 如果当前位置已经放过数据了，则直接跳过
                if (board[i][j] != '.') {
                    continue;
                }

                // 如果当前位置为空即'.'，判断当前位置是否适合放某个数字（从1-9）
                for (char num = '1'; num <= '9'; num++) {
                    // 校验[i,j]这个位置放这个数字是否合适，如果不合适则跳过
                    if (!validBoard(board, i, j, num)) {
                        continue;
                    }

                    // 如果这个位置存放当前数字合适，则做相应处理（节点处理、递归、回溯）
                    board[i][j] = num;
                    if (backTrack(board)) {
                        return true; // 如果找到合适的立刻返回 backTrack(board);
                    }
                    board[i][j] = '.';
                }
                // 9个数都试完了，没找到合适的数存放，说明该方案不行
                return false;
            }
        }
        // for 循环遍历结束没有返回false，说明找到了合适的棋盘位置
        return true;
    }

    // 校验九宫格是否有效
    public boolean validBoard(char[][] board, int row, int col, int val) {
        // 校验行
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == val) {
                return false; // 遍历到指定行的某一列发现该元素已经出现过了
            }
        }

        // 校验列
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == val) {
                return false; // 遍历到指定列的某一行发现该元素已经出现过了
            }
        }

        // 校验九宫格
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) { // 判断9方格里是否重复
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == val) {
                    return false;
                }
            }
        }
        return true;
    }

}