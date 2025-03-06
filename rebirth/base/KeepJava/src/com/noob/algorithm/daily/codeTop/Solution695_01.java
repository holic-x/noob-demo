package com.noob.algorithm.daily.codeTop;

import java.util.Map;

public class Solution695_01 {

    // 定义4个方向
    public int[][] dirs = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    // 定义当前遍历岛屿面积
    public int curIslandArea = 0;

    /**
     * 思路分析：遍历每个可能的陆地起点，基于该起点渲染岛屿覆盖的陆地（标记已访问过的陆地）
     */
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int maxArea = 0; // 记录最大岛屿面积
        // 初始化标记数组（初始化均为false）
        boolean[][] visited = new boolean[m][n];

        // 遍历每个可能的陆地起点
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果当前遍历区域为陆地且没有被访问过则可选其作为起点出发
                if (grid[i][j] == 1 && !visited[i][j]) {
                    // 每次处理新的岛屿时重置面积计数器
                    curIslandArea = 0;
                    dfs(grid, i, j, visited);
                    maxArea = Math.max(maxArea, curIslandArea); // 递归完成更新最大岛屿面积
                }
            }
        }

        // 返回结果
        return maxArea;
    }

    /**
     * dfs 方式遍历岛屿
     * x,y 当前访问区域的坐标
     * visited 用于标记已经访问过的陆地
     */
    public void dfs(int[][] grid, int x, int y, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        // 递归出口
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return; // 坐标越界，退出
        }
        // 如果当前节点区域为非陆地或者已经被遍历过也无需处理
        if (grid[x][y] == 0 || visited[x][y]) {
            return; // 无需处理
        }

        // 处理节点
        visited[x][y] = true; // 标记当前区域为已遍历
        curIslandArea++; // 岛屿面积+1


        // 递归向4个方向渲染
        for (int i = 0; i < 4; i++) {
            int nextX = x + dirs[i][0];
            int nextY = y + dirs[i][1];
            // 递归处理
            dfs(grid, nextX, nextY, visited);
        }

    }
}
