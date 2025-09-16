package com.noob.algorithm.daily.plan03.hot100_daily.day13;

/**
 * 🟡 200 岛屿数量 - https://leetcode.cn/problems/number-of-islands/
 */
public class Solution200_01 {

    private int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * 思路分析：
     */
    public int numIslands(char[][] grid) {

        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        // 遍历每个可能的岛屿起点，然后处理节点
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    dfs(grid, i, j, visited);
                    cnt++; // 岛屿数+1
                }
            }
        }

        return cnt;

    }

    // 面积记录
    // private int curArea = 0;

    // bfs 遍历
    private void dfs(char[][] grid, int x, int y, boolean[][] visited) {
        // 越界校验
        int m = grid.length, n = grid[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return;
        }
        // 非陆地或者已经被处理过则不纳入
        if (grid[x][y] != '1' || visited[x][y]) {
            return;
        }

        // 处理区域
        visited[x][y] = true;
       //  curArea++;

        // 递归处理（向4个方向渲染）
        for (int i = 0; i < 4; i++) {
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            // 递归处理
            dfs(grid, nextX, nextY, visited);
        }
    }


}
