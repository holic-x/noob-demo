package com.noob.algorithm.daily.day13;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟡 200 岛屿数量 - https://leetcode.cn/problems/number-of-islands/
 */
public class Solution200_02 {

    // 4个方向定义
    int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    // 岛屿数量统计
    public int cnt;

    /**
     * 遍历每个可能的陆地起点，基于该起点进行周边渲染
     */
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        // 初始化visited[][]
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(visited[i], false);
        }

        // 遍历每个可能的陆地起点
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) { // 如果为未被访问过的陆地，则基于其出发进行渲染
                    bfs(grid, i, j, visited);
                    cnt++; // 岛屿数量统计
                }
            }
        }

        // 返回岛屿数量
        return cnt;
    }

    // 递归遍历
    public void bfs(char[][] grid, int x, int y, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        // 构建队列辅助遍历
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(x, y));
        visited[x][y] = true; // 加入队列时及时标记访问状态，避免重复访问

        // 遍历队列
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            int curX = cur.x;
            int curY = cur.y;
            // 继续往四个方向渲染
            for (int i = 0; i < 4; i++) {
                int nextX = curX + dirs[i][0];
                int nextY = curY + dirs[i][1];
                // 校验是否越界、或者遇到非陆地、已经被访问过的陆地都跳过
                if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || grid[nextX][nextY] == '0' || visited[nextX][nextY]) {
                    continue; // 跳过
                } else {
                    // 将其加入队列并及时标记访问状态
                    queue.offer(new Pair(nextX, nextY));
                    visited[nextX][nextY] = true; // 及时同步访问状态
                }
            }
        }
    }

    // 自定义坐标类
    class Pair {
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
