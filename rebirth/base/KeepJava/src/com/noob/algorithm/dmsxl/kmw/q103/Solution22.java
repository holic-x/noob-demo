package com.noob.algorithm.dmsxl.kmw.q103;


import com.noob.algorithm.dmsxl.graph.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 103 水流问题
 * 水只能流向更低的相邻节点
 */
public class Solution22 {

    static int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};// 四个方向

    /**
     * bfs 广搜
     *
     * @param graph   二维矩阵
     * @param visited 已遍历节点
     * @param x、y     当前遍历节点
     */
    public static void bfs(int[][] graph, boolean[][] visited, int x, int y) {
        int n = graph.length, m = graph[0].length;

        // 构建辅助队列
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(x, y)); // 初始化
        visited[x][y] = true; // 只要入队就立刻标记

        // 遍历队列
        while (!queue.isEmpty()) {
            // 取出节点
            Pair curPair = queue.poll();
            int curX = curPair.x;
            int curY = curPair.y;

            // 分别向4个方向检索
            for (int i = 0; i < 4; i++) {
                int nextX = curX + dir[i][0];
                int nextY = curY + dir[i][1];
                // 判断是否越界
                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) {
                    continue; // 越界则跳过
                }
                // 判断是否满足流向规则
                if (graph[curX][curY] > graph[nextX][nextY]) {
                    continue; // 此处校验的是从低到高流向
                }
                // 如果节点没有被遍历过则加入队列并进行标记
                if (!visited[nextX][nextY]) {
                    // 满足条件则加入队列并标记
                    queue.offer(new Pair(nextX, nextY));
                    visited[nextX][nextY] = true; // 只要入队就立刻标记
                }
            }
        }
    }

    public static void main(String[] args) {
        // 1.输入控制
        int[][] graph = {
                {1, 3, 1, 2, 4}, {1, 2, 1, 3, 2}, {2, 4, 7, 2, 1},
                {4, 5, 6, 1, 1,}, {1, 4, 1, 2, 1}
        };
        int n = graph.length, m = graph[0].length;

        // 2.分别从第1组边界、第2组边界出发，记录已遍历节点
        boolean[][] firstBorder = new boolean[n][m];
        boolean[][] secondBorder = new boolean[n][m];

        // 遍历行：左（第1组边界）、右（第2组边界）
        for (int i = 0; i < n; i++) {
            bfs(graph, firstBorder, i, 0); // 左（第1组边界）
            bfs(graph, secondBorder, i, m - 1); // 右（第2组边界）

        }

        // 遍历列：上（第1组边界）、下（第2组边界）
        for (int j = 0; j < m; j++) {
            bfs(graph, firstBorder, 0, j); // 上（第1组边界）
            bfs(graph, secondBorder, n - 1, j); // 下（第2组边界）
        }

        // 3.判断这两个标记数组是否存在公共已遍历节点，如果存在则说明这个公共节点是既可达边界1又可达边界2的
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (firstBorder[i][j] && secondBorder[i][j]) {
                    System.out.println("i:" + i + "  j:" + j);
                }
            }
        }

    }

}
