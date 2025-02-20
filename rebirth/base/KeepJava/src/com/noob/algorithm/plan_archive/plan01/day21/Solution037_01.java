package com.noob.algorithm.plan_archive.plan01.day21;

/**
 * 🔴 037 解数独
 */
public class Solution037_01 {


    /**
     * 回溯思路：为每个没有填写数字的宫格尝试填充数字（1-9）
     */
    public void solveSudoku(char[][] board) {
        // 调用回溯算法处理
        backTrack(board);
    }

    // 回溯算法
    public boolean backTrack(char[][] board) {
        int n = board.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 如果当前位置已经放过数字则跳过
                if (board[i][j] != '.') {
                    continue;
                }

                // 从1-9个数字中选择
                for (char x = '1'; x <= '9'; x++) {
                    if (isValid(board, i, j, x)) { // 空位校验，满足放置条件才进一步处理
                        board[i][j] = x; // 填充
                        if (backTrack(board)) {
                            return true; // 如果回溯找到满足的位置则返回
                        }
                        board[i][j] = '.'; // 恢复现场
                    }
                }
                // 9个数字都试完了，没有找到合适的数存放，该方案不可行，返回false
                return false;
            }
        }
        // for 循环遍历结束没有返回false，说明找到了合适的棋盘位置
        return true;
    }


    /**
     * 校验当前填充位置是否满足九宫格要求：
     * ① 同行数字1-9只能出现一次
     * ② 同列数字1-9只能出现一次
     * ③ 3*3 宫内数字1-9只能出现一次
     * board 当前九宫格状态，row/col 当前选择的行和列，val 当前要填充的数字
     */
    public boolean isValid(char[][] board, int row, int col, int val) {
        // 同行校验是否已经出现过val
        for (int j = 0; j < 9; j++) {
            if (board[row][j] == val) {
                return false;
            }
        }

        // 同列校验是否已经出现过val
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == val) {
                return false;
            }
        }

        // 3*3九宫格内校验是否已经出现过val(i∈[(row/3)*3,(row/3)*3 + 3],j∈[(col/3)*3,(col/3)*3 + 3])
        for (int i = (row / 3) * 3; i < (row / 3) * 3 + 3; i++) {
            for (int j = (col / 3) * 3; j < (col / 3) * 3 + 3; j++) {
                if (board[i][j] == val) {
                    return false;
                }
            }
        }

        // 校验通过，该位置可放置该数字
        return true;
    }

}
