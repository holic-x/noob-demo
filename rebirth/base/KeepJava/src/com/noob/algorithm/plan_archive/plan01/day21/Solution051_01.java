package com.noob.algorithm.plan_archive.plan01.day21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🔴 051 N 皇后
 */
public class Solution051_01 {

    public List<List<String>> res = new ArrayList<>(); // 结果集
    // public List<String> curPath = new ArrayList<>(); // 当前方案

    public List<List<String>> solveNQueens(int n) {
        // 初始化二维矩阵存储棋盘状态（Q表示皇后，. 表示空位）
        char[][] chessBoard = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(chessBoard[i], '.');
        }

        // 回溯核心：为每一行选择1个位置存储（校验每一行的每一个位置是否满足存放条件，满足则放置）
        backTrack(chessBoard, 0, n); // 从第0行开始调用(在回溯过程中调整row的值)
        // 返回处理结果
        return res;
    }

    // 定义回溯方法处理N皇后问题（二维矩阵处理）
    public void backTrack(char[][] chessBoard, int row, int n) {
        if (row >= n) {
            // 载入结果集并退出
            // res.add(new ArrayList<>(curPath));
            res.add(transferChessBoard(chessBoard));
            return;
        }

        // 回溯核心：为每一行选择1个位置存储（校验每一行的每一个位置是否满足存放条件，满足则放置）
        for (int col = 0; col < n; col++) {
            // 校验当前选中位置是否满足放置条件
            if (isValid(chessBoard, row, col)) {
                chessBoard[row][col] = 'Q'; // 放置棋子
                backTrack(chessBoard, row + 1, n); // 调用回溯算法
                chessBoard[row][col] = '.'; // 恢复现场
            }
        }
    }

    /**
     * 校验当前选中位置是否满足放置条件
     *
     * @param chessBoard 当前棋盘状态
     * @param row        col 选择的行、列
     */
    public boolean isValid(char[][] chessBoard, int row, int col) {
        // ① 同行校验（row已经指定是不同行，此处无需重复校验）

        // ② 同列校验（当前row以上的该列校验）
        for (int i = 0; i < row; i++) {
            if (chessBoard[i][col] == 'Q') {
                return false; // 如果该列已经存在皇后，则该位置不可放置
            }
        }

        // ③ 左斜下对角线校验（从[row,col]位置出发开始校验）
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessBoard[i][j] == 'Q') {
                return false; // 如果对角线位置已经存在皇后，则该位置不可放置
            }
        }


        // ④ 右斜上对角线校验（从[row,col]位置出发开始校验）
        for (int i = row - 1, j = col + 1; i >= 0 && j < chessBoard.length; i--, j++) {
            if (chessBoard[i][j] == 'Q') {
                return false; // 如果对角线位置已经存在皇后，则该位置不可放置
            }
        }

        // 位置校验可行
        return true;
    }

    // 将棋盘状态转化为字符串列表存储
    public static List<String> transferChessBoard(char[][] chessBoard) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < chessBoard.length; i++) {
            StringBuffer buffer = new StringBuffer();
            for (int j = 0; j < chessBoard[i].length; j++) {
                buffer.append(chessBoard[i][j]);
            }
            board.add(buffer.toString());
        }
        return board;
    }

    public static void main(String[] args) {
        Solution051_01 solution = new Solution051_01();
        solution.solveNQueens(4);
    }

}
