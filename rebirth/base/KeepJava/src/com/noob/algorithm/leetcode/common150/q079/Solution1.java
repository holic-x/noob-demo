package com.noob.algorithm.leetcode.common150.q079;

/**
 * 079 单词搜索
 */
public class Solution1 {

    // 遍历二维矩阵的每个元素作为起点，确认其dfs是否存在路径满足匹配word序列
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(i, j, board, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 辅助方法构建字符序列
    public boolean dfs(int row, int col, char[][] board, String word, int idx) {
        // 结束条件（遇到边界（索引为负数或者超出长度）、当前元素与目标元素不符、当前元素已被访问则返回false；如果遍历到word的最后一位则说明存在匹配word则返回true）
        // 返回false的情况
        if (row >= board.length || row < 0 || col >= board[row].length || col < 0 || board[row][col] != word.charAt(idx) || board[row][col] == '0') {
            return false;
        }
        // 返回true的情况
        if (idx == word.length() - 1) {
            return true;
        }

        // 如果当前遍历元素和idx指向的word对应元素匹配则可继续进行检索判断下一个元素是否匹配（将元素进行标记，然后往4个方向进行判断）
        char cur = board[row][col]; // 记录当前值
        board[row][col] = '0'; // 将该值标记为已遍历
        // 分别向4个方向进行遍历（上下左右）
        if (dfs(row - 1, col, board, word, idx + 1) || dfs(row + 1, col, board, word, idx + 1)
                || dfs(row, col - 1, board, word, idx + 1) || dfs(row, col + 1, board, word, idx + 1)) {
            return true; // 如果四个方向中有一个方向满足，则继续往下
        }
        board[row][col] = cur; // 复原现场
        return false;
    }

}
