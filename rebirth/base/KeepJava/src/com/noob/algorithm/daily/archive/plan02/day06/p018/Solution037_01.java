package com.noob.algorithm.daily.archive.plan02.day06.p018;

/**
 * 🔴 037 解数独 - https://leetcode.cn/problems/sudoku-solver/description/
 */
public class Solution037_01 {

    /**
     * 思路分析：解数独
     * 有效数字集合[1,9],每一行、每一列、3*3分割的区间内均值能出现有效数字集合中的1个
     */
    public void solveSudoku(char[][] board) {
        // 调用回溯算法
        backTrack(board);
    }

    // 定义回溯算法
    private boolean backTrack(char[][] board) {

        // 所有元素处理完成可以得到一个结果集

        // 回溯处理
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // 校验当前空格位置是否需要填入数字，且校验[1,9]集合范围内依次填入校验
                if (board[i][j] == '.') {
                    for (char num = '1'; num <= '9'; num++) {
                        if (isValid(board, num, i, j)) {
                            board[i][j] = num; // 选择当前位置放入num
                            if (backTrack(board)) {
                                // 递归处理下一个位置,如果出现满足条件的位置，即刻返回结果
                                return true;
                            }
                            board[i][j] = '.'; // 恢复现场
                        }
                    }
                    // 9个数字都处理完了，没有一个合适的存放位置
                    return false;
                } else {
                    continue;
                }
            }
        }
        // for 循环遍历结束没有返回false，说明找到了合适的棋盘位置
        return true;
    }

    /**
     * 校验当前棋盘位置填入num后是否满足九宫格限制条件(即判断限定区域内是否已经出现过了num)
     */
    private boolean isValid(char[][] board, char num, int selectedRow, int selectCol) {
        // 校验同一行(此处是整行校验)
        for (int j = 0; j < 9; j++) {
            if (board[selectedRow][j] == num) {
                return false;
            }
        }

        // 校验同一列(此处是整列校验)
        for (int i = 0; i < 9; i++) {
            if (board[i][selectCol] == num) {
                return false;
            }
        }

        // 校验限定九宫格范围内(获取行、列其起点)
        int startRow = (selectedRow / 3) * 3, startCol = (selectCol / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        // 校验通过
        return true;
    }


}
