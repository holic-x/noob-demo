package com.noob.algorithm.daily.plan03.hot100_daily.day06.p018;


import java.util.ArrayList;
import java.util.List;

/**
 * 🔴 051 N皇后 - https://leetcode.cn/problems/n-queens/description/
 * 填充棋盘，确保每一行、每一列、同一斜线上只能出现1个皇后（避免相互攻击），得到不同的方案
 */
public class Solution051_01 {

    List<List<String>> ans = new ArrayList<>(); // 定义结果集

    /**
     * 思路分析：将n个皇后放在n*n的棋盘上，且使得皇后不能互相攻击
     */
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        // Arrays.fill(board, '.');
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        // 调用回溯算法
        backTrack(n, 0, board);
        // 返回结果
        return ans;
    }


    // 回溯思路处理
    private void backTrack(int n, int rowIdx, char[][] board) {

        if (rowIdx >= n) {
            // 遍历到尾行，记录当前棋盘位置
            ans.add(printBoard(board));
            return;
        }

        // 校验当前行可放置的位置：每一列都进行递归校验寻找到一个可放置的位置
        for (int col = 0; col < n; col++) {
            // 校验当前位置是否可放
            boolean validRes = valid(board, rowIdx, col);
            if (validRes) {
                board[rowIdx][col] = 'Q';// 放置棋子
                backTrack(n, rowIdx + 1, board); // 递归处理下一行
                board[rowIdx][col] = '.'; // 复原
            }
        }

    }

    // 打印棋盘
    private List<String> printBoard(char[][] board) {
        List<String> list = new ArrayList<>();
        /*
        for (int i = 0; i < board.length; i++) {
            StringBuffer buffer = new StringBuffer();
            for (int j = 0; j < board[0].length; j++) {
                buffer.append(board[i][j]);
            }
            list.add(buffer.toString());
        }
         */

        for (int i = 0; i < board.length; i++) {
            list.add(new String(board[i]));
        }
        return list;
    }


    private boolean valid(char[][] board, int row, int col) {
        int n = board.length;
        // 校验同行是否有棋子（row限制了一行只能放一个，此处不需要校验）

        // 校验同列是否有棋子
        for (int i = 0; i < n; i++) {
            if (board[i][col] == 'Q') {
                return false; // 同列有皇后，该位置不满足放置规则
            }
        }

        // 校验左上
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // 校验右上
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // 校验通过
        return true;
    }


    public static void main(String[] args) {
        int n = 4;
        Solution051_01 s = new Solution051_01();
        s.solveNQueens(n);
    }
}
