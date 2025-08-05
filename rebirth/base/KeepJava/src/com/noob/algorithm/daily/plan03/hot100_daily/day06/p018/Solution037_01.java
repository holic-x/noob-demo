package com.noob.algorithm.daily.plan03.hot100_daily.day06.p018;

/**
 * 🔴 037 解数独 - https://leetcode.cn/problems/sudoku-solver/description/
 * 有效数字集合[1,9],每一行、每一列、3*3分割的区间内均值能出现有效数字集合中的1个
 */
public class Solution037_01 {

    /**
     * 思路分析：解数独
     */
    public void solveSudoku(char[][] board) {
        backTrack(board);
    }

    // 回溯处理
    private boolean backTrack(char[][] board) {
        // 校验每一行、每一列可放置的位置，从1-9选择一个数字填充，随后校验这个填充位置是否可行
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boolean todo = (board[i][j] == '.'); // 表示当前该位置需要处理
                if (!todo) {
                    continue;
                }
                // 回溯处理：从'1'=>'9'中选择一个数字，并且验证该数字所处位置的九宫格范围内是否满足数独规则
                for (char c = '1'; c <= '9'; c++) {
                    boolean validRes = valid(board, i, j, c);
                    if (validRes) {
                        board[i][j] = c; // 放置
                        // backTrack(board); // 递归处理下一个位置
                        if(backTrack(board)){ // 递归校验：如果出现了满足条件的九宫格棋盘则直接返回结果
                            return true;
                        }
                        board[i][j] = '.'; // 复原
                    }
                }
                // 9个元素都校验完毕也没有找到合适的位置填充，说明这条路走不通
                return false;
            }
        }
        // for 循环遍历结束没有返回false，说明找到了合适的棋盘位置
        return true;
    }

    // 验证当前位置[i,j]坐标所在的九宫格位置是否满足数读规则
    private boolean valid(char[][] board, int i, int j, char ch) {
        // 校验同一行
        for (int col = 0; col < 9; col++) {
            if (board[i][col] == ch) {
                return false;
            }
        }

        // 校验同一列
        for (int row = 0; row < 9; row++) {
            if (board[row][j] == ch) {
                return false;
            }
        }


        /**
         * [i,j] 所在的九宫格的有效范围：
         * row：i/3,i/3+3
         * col：j/3,j/3+3
         * 检验放置位置是否满足，则只需要校验九宫格范围内除了当前位置是否已经出现过该ch
         */
        int rStart = (i / 3) * 3, rEnd = rStart + 3;
        int cStart = (j / 3) * 3, cEnd = cStart + 3;
        for (int row = rStart; row < rEnd; row++) {
            for (int col = cStart; col < cEnd; col++) {
                if (board[row][col] == ch) {
                    return false; // 校验不通过
                }
            }
        }
        return true;
    }


}
