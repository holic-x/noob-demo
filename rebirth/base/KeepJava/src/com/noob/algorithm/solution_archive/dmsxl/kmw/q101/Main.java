package com.noob.algorithm.solution_archive.dmsxl.kmw.q101;

import com.noob.algorithm.solution_archive.dmsxl.graph.GraphInputUtil;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static int count = 0;
    private static final int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}}; // 四个方向

    private static void bfs(int[][] grid, int x, int y) {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{x, y});
        grid[x][y] = 0; // 只要加入队列，立刻标记
        count++;
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curx = cur[0];
            int cury = cur[1];
            for (int i = 0; i < 4; i++) {
                int nextx = curx + dir[i][0];
                int nexty = cury + dir[i][1];
                if (nextx < 0 || nextx >= grid.length || nexty < 0 || nexty >= grid[0].length) continue; // 越界了，直接跳过
                if (grid[nextx][nexty] == 1) {
                    que.add(new int[]{nextx, nexty});
                    count++;
                    grid[nextx][nexty] = 0; // 只要加入队列立刻标记
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] grid = GraphInputUtil.getMatrixGraph(0);
//        int[][] graph = new int[][]{
//                {0, 1, 0, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}, {0, 1, 1, 1, 0, 1, 1, 1}, {0, 0, 0, 0, 1, 0, 0, 0},
//                {0, 1, 0, 0, 1, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0}, {0, 1, 1, 0, 0, 1, 1, 0}
//        };

        int n = grid.length, m = grid[0].length;
        
        // 从左侧边，和右侧边向中间遍历
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 1) bfs(grid, i, 0);
            if (grid[i][m - 1] == 1) bfs(grid, i, m - 1);
        }
        
        // 从上边和下边向中间遍历
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 1) bfs(grid, 0, j);
            if (grid[n - 1][j] == 1) bfs(grid, n - 1, j);
        }
        
        count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) bfs(grid, i, j);
            }
        }

        System.out.println(count);
    }
}