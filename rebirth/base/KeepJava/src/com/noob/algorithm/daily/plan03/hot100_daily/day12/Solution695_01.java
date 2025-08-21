package com.noob.algorithm.daily.plan03.hot100_daily.day12;

/**
 * 🟡 695 岛屿的最大面积 - https://leetcode.cn/problems/max-area-of-island/description/
 * - DFS 版本
 */
public class Solution695_01 {

    int[][] dir = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    /**
     * 遍历每一个可能的岛屿起点，计算内部岛屿的陆地面积（dfs讨论），遍历过程中需要注意已遍历节点的标记动作
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {

        // 遍历每个可能的岛屿起点，累计有效面积和
        // int area = 0; 累加面积和

        // 此处要计算的是最大岛屿面积
        int maxArea = 0;

        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) { // 陆地且未处理过
                    // 初始化面积计数器
                    curArea = 0;
                    dfs(grid, i, j, visited);
                    // 递归处理完成，累加岛屿面积
                    // area += curArea;
                    maxArea = Math.max(maxArea, curArea);
                }
            }
        }

        // 返回结果
        return maxArea;
    }

    int curArea = 0; // 记录每个岛屿的面积

    // dfs 处理
    private void dfs(int[][] grid, int x, int y, boolean[][] visited) {
        // 越界校验
        int m = grid.length, n = grid[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return;
        }

        // 判断节点是否为海域或者已经被处理过
        if (visited[x][y] || grid[x][y] == 0) {
            return;
        }


        // 如果是陆地则进一步处理
        if (grid[x][y] == 1) {
            // 处理当前节点
            visited[x][y] = true;
            curArea++; // 岛屿面积+1

            // 渲染邻接节点
            for (int i = 0; i < 4; i++) {
                int nextX = x + dir[i][0];
                int nextY = y + dir[i][1];
                dfs(grid, nextX, nextY, visited);
            }
        }
    }


}
