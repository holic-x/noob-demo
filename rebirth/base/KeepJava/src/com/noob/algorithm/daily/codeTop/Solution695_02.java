package com.noob.algorithm.daily.codeTop;

import java.util.LinkedList;
import java.util.Queue;

public class Solution695_02 {

    // 定义4个方向
    public int[][] dirs = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    // 定于当前遍历岛屿面积
    public int curIslandArea = 0;

    /**
     * 思路分析：遍历每个可能的陆地起点，基于该起点渲染岛屿覆盖的陆地（标记已访问过的陆地）
     */
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // 定义最大岛屿面积
        int maxArea = 0;

        // 定义标记数组
        boolean[][] visited = new boolean[m][n];

        // 遍历每个可能的陆地起点
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    curIslandArea = 0; // 重置岛屿面积计数器
                    bfs(grid, i, j, visited);
                    maxArea = Math.max(maxArea, curIslandArea); // 更新最大岛屿面积
                }
            }
        }

        // 返回结果
        return maxArea;
    }

    /**
     * bfs 方式遍历岛屿
     */
    public void bfs(int[][] grid, int x, int y, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;

        // 定义队列辅助渲染操作
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(x, y));
        visited[x][y] = true; // 入队时同步标记访问状态，避免重复渲染
        curIslandArea++; // 岛屿面积计数

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            // 校验4个方向
            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dirs[i][0];
                int nextY = cur.y + dirs[i][1];
                // 校验边界以及是否为陆地、是否被遍历过
                if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || grid[nextX][nextY] == 0 || visited[nextX][nextY]) {
                    continue;
                } else {
                    // 将当前坐标位置加入队列
                    queue.offer(new Pair(nextX, nextY));
                    visited[nextX][nextY] = true; // 同步标记访问状态
                    curIslandArea++; // 岛屿面积计数
                }
            }
        }
    }

    // 自定义坐标类
    class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
