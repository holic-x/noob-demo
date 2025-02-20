package com.noob.algorithm.daily.archive.plan02.hot100.day06.p018;

import java.util.*;

/**
 * 🔴 051 N皇后 - https://leetcode.cn/problems/n-queens/description/
 */
public class Solution051_01 {

    private List<List<String>> res = new ArrayList<>(); // 定义结果集
    // private List<String> path = new ArrayList<>(); // 定义路径（当前选中方案策略）

    /**
     * 思路分析：填充棋盘，确保每一行、每一列、同一斜线上只能出现1个皇后（避免相互攻击），得到不同的方案
     */
    public List<List<String>> solveNQueens(int n) {
        // 定义棋盘
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        // 调用回溯算法
        backTrack(0, n, board);
        // 返回结果集合
        return res;
    }

    // 定义回溯算法
    private void backTrack(int row, int n, char[][] board) {

        // 递归出口（处理结果集合）
        if (row >= n) {
            res.add(new ArrayList<>(getBoardPath(board))); // 当row遍历到末尾则加载结果集(将当前棋盘分布情况转化为List<String>形式载入)
            return; // 退出
        }

        // 回溯处理：idx(row)选定行，for敲定列
        for (int i = 0; i < n; i++) {
            // 校验当前选定位置是否满足放置条件，如果满足则进一步递归获取下一行的可放置位置
            if (isValid(board, row, i)) {
                board[row][i] = 'Q'; // 选择该位置
                backTrack(row + 1, n, board); // 递归处理下一行
                board[row][i] = '.'; // 恢复现场
            }
        }
    }

    // 将当前棋盘转化为字符串列表形式
    private List<String> getBoardPath(char[][] board) {
        int n = board.length;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuffer temp = new StringBuffer();
            for (int j = 0; j < n; j++) {
                temp.append(board[i][j]);
            }
            list.add(new String(temp.toString()));
        }
        // 返回转化结果
        return list;
    }

    // 校验当前选定行、列是否满足放置条件
    private boolean isValid(char[][] board, int selectedRow, int selectedCol) {
        // 同一行不能出现Q（校验有效范围：列[0,selectedCol-1]）(上述回溯限定1行只能选1个，则此条件必然满足，可无需校验)
        for (int j = 0; j <= selectedCol - 1; j++) {
            if (board[selectedRow][j] == 'Q') {
                return false;
            }
        }

        // 同一列不能出现Q（校验有效范围：行[0,selectedRow-1]）
        for (int i = 0; i <= selectedRow - 1; i++) {
            if (board[i][selectedCol] == 'Q') {
                return false;
            }
        }

        // 校验同一斜线上不能出现Q（以当前选择节点出发，校验其左上、右上的内容）
        for (int i = selectedRow - 1, j = selectedCol - 1; i >= 0 && j >= 0; i--, j--) { // 左上
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = selectedRow - 1, j = selectedCol + 1; i >= 0 && j <= board.length - 1; i--, j++) { // 右上
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
