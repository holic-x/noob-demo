package com.noob.algorithm.daily.plan03.hot100_daily.day12;

/**
 * 🟡 200 岛屿数量 - https://leetcode.cn/problems/number-of-islands/description/
 */
public class Solution200_01 {

    // 定义4个方向
    private int[][] dir = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    // 统计岛屿数
    private int cnt = 0;

    /**
     * 思路分析：
     * - 遍历每个可能的"岛屿起点",随后向4个方向扩展校验是否连接陆地，如果连接则进行渲染
     */
    public int numIslands(char[][] grid) {

        // 定义标记数组
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n]; // 默认全为未遍历(还未渲染)

        // 遍历每个可能的起点
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    dfs(i, j, grid, visited);
                    cnt++; // 处理完成 岛屿数+1
                }
            }
        }

        return cnt;
    }


    private void dfs(int x, int y, char[][] grid, boolean[][] visited) {
        // 校验节点是否越界
        int m = grid.length, n = grid[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return; // 越界，退出
        }

        // 判断当前节点是否已被渲染或者为海洋
        if (visited[x][y] || grid[x][y] == '0') {
            return; // 当前节点已被渲染或者为海洋，退出
        }

        // 标记当前节点
        visited[x][y] = true;

        // 递归处理，向4个方向处理
        for (int i = 0; i < dir.length; i++) {
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            // 递归处理
            dfs(nextX, nextY, grid, visited);
        }
    }

}
