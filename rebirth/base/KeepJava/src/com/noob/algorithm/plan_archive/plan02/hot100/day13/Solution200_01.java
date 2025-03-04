package com.noob.algorithm.plan_archive.plan02.hot100.day13;

import java.util.Arrays;

/**
 * 🟡 200 岛屿数量 - https://leetcode.cn/problems/number-of-islands/
 */
public class Solution200_01 {

    // 4个方向定义
    int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    // 岛屿数量统计
    public int cnt;

    /**
     * 遍历每个可能的陆地起点，基于该起点进行周边渲染
     */
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        // 定义visited矩阵，用于标记岛屿被访问
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(visited[i], false);
        }

        // 遍历每个可能的陆地起点
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) { // 当前遍历区域为陆地，且没有被访问过
                    dfs(grid, i, j, visited);
                    cnt++; // 岛屿数量加1
                }
            }
        }

        // 返回结果
        return cnt;
    }

    // 递归遍历
    public void dfs(char[][] grid, int x, int y, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        // 递归出口：校验节点是否越界、当前区域是否为海洋或者已经被遍历过
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || grid[x][y] == '0') {
            return; // 越界
        }
        // 经过上述条件过滤，如果为未被遍历的陆地，则进行标记渲染
        visited[x][y] = true;

        // 递归处理相邻的区域
        for (int i = 0; i < 4; i++) {
            int nextX = x + dirs[i][0];
            int nextY = y + dirs[i][1];
            // 递归处理相邻节点
            dfs(grid, nextX, nextY, visited);
        }
    }

}
