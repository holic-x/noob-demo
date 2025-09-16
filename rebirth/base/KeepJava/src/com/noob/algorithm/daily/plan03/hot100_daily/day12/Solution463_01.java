package com.noob.algorithm.daily.plan03.hot100_daily.day12;

/**
 * 🟢 463 岛屿的周长 - https://leetcode.cn/problems/island-perimeter/description/
 */
public class Solution463_01 {

    private int[][] dir = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    /**
     * 思路分析
     * 解答思路：打破常规的岛屿问题模型，此处计算岛屿周长本质上就是计算【每块陆地的 邻接海域/邻接边缘 累加和】
     */
    public int islandPerimeter(int[][] grid) {

        // 遍历每个节点，统计周长和
        int m = grid.length, n = grid[0].length;
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cnt += getCnt(grid, i, j);
            }
        }

        return cnt;
    }

    // 校验未被访问的陆地的四周的邻接海域/邻接边缘，并返回统计和
    private int getCnt(int[][] grid, int x, int y) {
        int m = grid.length, n = grid[0].length;
        // 校验是否越界，越界则不统计
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return 0; // 不统计
        }

        int curCnt = 0;

        // 校验是否为陆地
        if (grid[x][y] == 1) {

            // 4个方向处理
            for (int i = 0; i < 4; i++) {
                int nextX = x + dir[i][0];
                int nextY = y + dir[i][1];

                // 校验当前节点是否邻接海域或边界（邻接海域则说明`grid[nextX][nextY]==0` 邻接边界则说明`nextX\nextY越界`）
                if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) { // 优先校验边界避免越界讨论
                    curCnt++;
                    continue;
                }

                if (grid[nextX][nextY] == 0) {
                    curCnt++;
                }

            }

        }

        // 返回统计的周长
        return curCnt;

    }


}
