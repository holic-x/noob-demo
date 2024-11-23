package com.noob.algorithm.dmsxl.leetcode.q062;

/**
 * 062 不同路径
 */
public class Solution1 {

    public int uniquePaths(int m, int n) {
        return dfs(0, 0, m, n);
    }

    /**
     * 深度优先搜索
     * i、j 表示当前遍历索引位置，m、n为对应边界
     */
    public int dfs(int i, int j, int m, int n) {
        // 递归出口
        if (i > m || j > n) {
            return 0; // 越界
        }
        // 走到右下角，说明找到一条路径
        if (i == m && j == n) {
            return 1;
        }
        // 递归
        return dfs(i + 1, j, m, n) + dfs(i, j + 1, m, n);
    }
}
