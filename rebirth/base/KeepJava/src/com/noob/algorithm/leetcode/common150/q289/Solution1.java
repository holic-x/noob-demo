package com.noob.algorithm.leetcode.common150.q289;

import java.util.Arrays;

/**
 * 289 生命游戏
 */
public class Solution1 {
    /**
     * 思路：copy一份数组进行模拟更新
     */
    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;

        // 构建一个新矩阵（外层填充0，内层存储元素）：因为只有活细胞数量才会对细胞状态有影响，因此周边填充0
        int[][] tempBoard = new int[m+2][n+2];
        for (int i = 0; i < m + 2; i++) {
            Arrays.fill(tempBoard[i], 0);
        }

        // 填充内存元素（对应tempBoard[1-m][1-n]范围数据）
        for (int i = 0; i < m; i++) {
            /*
            for (int j = 0; j < n; j++) {
                tempBoard[i+1][j+1] = board[i][j];
            }
             */
            System.arraycopy(board[i], 0, tempBoard[i + 1], 1, n); // 内层对照填充元素
        }

        // 根据规则更新细胞存活状态：计算每个元素位置周围8个格子的细胞存活数量
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 判断当前newBoard对应位置细胞存活状态，更新源矩阵
                int aliveCount = 0;
                // 统计当前细胞周围8个位置的活细胞数量
                aliveCount = tempBoard[i - 1][j - 1] + tempBoard[i - 1][j] + tempBoard[i - 1][j + 1]
                        + tempBoard[i][j - 1] + tempBoard[i][j + 1]
                        + +tempBoard[i + 1][j - 1] + tempBoard[i + 1][j] + tempBoard[i + 1][j + 1];
                // 根据规则判断存活状态，然后进行更新
                if (tempBoard[i][j] == 1 && aliveCount < 2) {
                    board[i-1][j-1] = 0;
                } else if (tempBoard[i][j] == 1 && ( aliveCount == 2 || aliveCount == 3)) {
                    board[i-1][j-1] = 1;
                } else if (tempBoard[i][j] == 1 &&  aliveCount > 3) {
                    board[i-1][j-1] = 0;
                } else if (tempBoard[i][j] == 0 && aliveCount == 3) {
                    board[i-1][j-1] = 1;
                }
            }
        }
    }
}
