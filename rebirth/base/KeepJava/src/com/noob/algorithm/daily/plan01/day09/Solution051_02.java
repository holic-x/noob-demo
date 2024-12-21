package com.noob.algorithm.daily.plan01.day09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 051 N皇后
 */
public class Solution051_02 {

    List<List<String>> res = new ArrayList<>(); // 定义结果集
    // List<String> path = new ArrayList<>(); // 定义当前选择路径（每个String表示每一行N皇后的选择方案）

    public List<List<String>> solveNQueens(int n) {
        // ① 初始化棋盘
        char[][] chessBoard = new char[n][n];
        for (char[] row : chessBoard) {
            Arrays.fill(row, '.');
        }
        // ② 调用回溯算法
        backTrack(n, 0, chessBoard);
        // ③ 返回结果
        return res;
    }

    /**
     * 定义回溯算法：由于N皇后存放的限制条件需要校验当前的整合棋盘的行、列、对角线，因此构建二维矩阵处理会比较方便
     * 如果只是存储curPath路径，则还要根据目前现有的res去复盘整个棋局，不如直接用二维矩阵处理，然后再将最终生成的二维矩阵转化为结果集
     *
     * @param n   矩阵大小
     * @param row 当前遍历行
     * @param chessBoard 棋盘
     */
    public void backTrack(int n, int row, char[][] chessBoard) {
        // 递归出口
        if (row == n) {
            // 将当前路径封装到res(记录当前棋盘的状态)
            res.add(outputChess(chessBoard));
            return;
        }

        // 递归处理
        for (int j = 0; j < n; j++) { // 每一行从[0,n)中择选放置的列
            if (isValid(chessBoard, row, j)) { // 校验当前位置放置棋子是否合理，如果合理才放置
                // 处理
                chessBoard[row][j] = 'Q';
                // 递归
                backTrack(n, row + 1, chessBoard);
                // 回溯
                chessBoard[row][j] = '.';
            }
        }
    }

    /**
     * 校验当前放置N皇后位置是否合理(遍历判断整个棋盘，不能同行、同列、同斜线方向)
     * @param chessBoard 棋盘
     * @param row   当前选择的行
     * @param col   当前选择的列
     */
    public boolean isValid(char[][] chessBoard, int row, int col) {
        // 同行校验（同行校验范围：列[0,j)）：回溯过程限定了每一列按列处理，确保了同一行不会出现多个Q，此处可以省略校验

        // 同列校验（同列校验范围：行[0,row)）
        for (int i = 0; i < row; i++) {
            if (chessBoard[i][col] == 'Q') {
                return false; // 同列位置上已存在`Q`，这个位置不符合放置条件
            }
        }

        // 同斜线方向校验(左上对角线：[0,0]-[row-1,col-1]、右上对角线：[row-1,col+1]-[0,n-1])
        // ① 左上对角线校验：从下到上，从右到左
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessBoard[i][j] == 'Q') {
                return false; // 45度对角出现过了皇后，因此这个位置不能放
            }
        }

        // ② 右上对角线：从下到上，从左到右
        for (int i = row - 1, j = col + 1; i >= 0 && j < chessBoard.length; i--, j++) {
            if (chessBoard[i][j] == 'Q') {
                return false; // 右上对角线位置上已存在Q，这个位置不符合放置条件
            }
        }

        // 通过上述校验
        return true;
    }

    // 将当前棋盘放置结果输出
    public List<String> outputChess(char[][] chessBoard) {
        List<String> list = new ArrayList<>();
        for (char[] row : chessBoard) {
            list.add(String.copyValueOf(row)); // char 处理：String.copyValueOf(c)
        }
        return list;
    }

//    public static void main(String[] args) {
//        Solution051_02 s = new Solution051_02();
//        s.solveNQueens(4);
//    }
}
