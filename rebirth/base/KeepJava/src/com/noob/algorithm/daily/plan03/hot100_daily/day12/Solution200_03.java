package com.noob.algorithm.daily.plan03.hot100_daily.day12;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟡 200 岛屿数量 - https://leetcode.cn/problems/number-of-islands/description/
 */
public class Solution200_03 {

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

        // 遍历每个可能的起点 : BFS 思路
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    bfs(i, j, grid, visited);
                    cnt++; // 处理完成 岛屿数+1
                }
            }
        }

        return cnt;
    }

    private void bfs(int x, int y, char[][] grid, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return;
        }

        // 向4个方向遍历
        Queue<int[]> queue = new LinkedList<>();
        // 初始化队列
        visited[x][y] = true;
        queue.offer(new int[]{x, y});

        // 队列不为空 取出节点进行4个方向渲染
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int curX = node[0];
            int curY = node[1];

            for (int i = 0; i < 4; i++) {
                int nextX = curX + dir[i][0];
                int nextY = curY + dir[i][1];

                // 校验越界
                if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) {
                    continue;
                }

                // 标记节点
                if (!visited[nextX][nextY] && grid[nextX][nextY] == '1') {
                    visited[nextX][nextY] = true; // 标记节点
                    queue.offer(new int[]{nextX, nextY}); // 载入列表
                }
            }

        }
    }


}
