package com.noob.algorithm.daily.plan01.day09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution051_01 {
    
    List<List<String>> res = new ArrayList<>(); // 定义结果集
    // List<String> curPath = new ArrayList<>(); // 定义当前遍历路径

    public List<List<String>> solveNQueens(int n) {
        // n表示矩阵大小，n×n矩阵（限定树的高度和宽度）

        // 初始化棋盘
        char[][] chessBoard = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(chessBoard[i], '.');
        }

        // 调用回溯算法
        backTrack(n, 0, chessBoard);
        // 返回结果集
        return res;

    }

    /**
     * 定义回溯算法：由于N皇后存放的限制条件需要校验当前的整合棋盘的行、列、对角线，因此构建二维矩阵处理会比较方便
     * 如果只是存储curPath路径，则还要根据目前现有的res去复盘整个棋局，不如直接用二维矩阵处理，然后再将最终生成的二维矩阵转化为结果集
     *
     * @param n   矩阵大小
     * @param row 当前遍历行
     */
    public void backTrack(int n, int row, char[][] chessBoard) { //
        // 终止条件（当递归到叶子结点，即路径节点数和n相同时视为遍历到叶子节点，此时记录当前的结果集）
        if (row == n) { // curPath.size()==n
            // res.add(new ArrayList<>(curPath));
            // 记录当前棋盘（即将二维矩阵转为结果集存储）
            res.add(Array2List(chessBoard));

            // 退出
            return;
        }

        // 回溯核心(校验指定行的所有列)
        for (int col = 0; col < n; col++) {

            // 剪枝处理（如果校验到当前这行不满足存放要求则跳过）
            if (!validOper(row, col, chessBoard)) {
                continue; // 跳过
            }

            // 处理节点
            chessBoard[row][col] = 'Q';
            // 递归
            backTrack(n, row + 1, chessBoard);
            // 回溯（恢复算法）
            chessBoard[row][col] = '.';
        }

    }

    /**
     * 校验当前位置是否可以放置（即判断同行、同列、对角线是否已经存在皇后，存在则不能存放）
     * 返回true表示可以放置，返回false表示不可放置
     */
    public boolean validOper(int row, int col, char[][] chessBoard) {
        // 校验同一列
        for (int i = 0; i < row; i++) { // 限定校验范围（还没遍历到的位置不需要校验）
            if (chessBoard[i][col] == 'Q') {
                return false; // 同一列出现过了皇后，因此这个位置不能放
            }
        }

        // 校验[i,j]左上角对角线元素
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessBoard[i][j] == 'Q') {
                return false; // 45度对角出现过了皇后，因此这个位置不能放
            }
        }

        // 校验[i,j]右上角对角线位置元素
        int n = chessBoard.length;
        for (int i = row - 1, j = col + 1; i >= 0 && j <= n - 1; i--, j++) {
            if (chessBoard[i][j] == 'Q') {
                return false; // 135度对角出现过了皇后，因此这个位置不能放
            }
        }

        // 校验通过
        return true;
    }


    public List Array2List(char[][] chessboard) {
        List<String> list = new ArrayList<>();

        for (char[] c : chessboard) {
            list.add(String.copyValueOf(c)); // char 处理：String.copyValueOf(c)
        }

        return list;
    }

}
