package com.noob.algorithm.solution_archive.dmsxl.kmw.q103;


import com.noob.algorithm.solution_archive.dmsxl.graph.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 103 水流问题
 * 水只能流向更低的相邻节点
 */
public class Solution12 {

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
                if (graph[curX][curY] < graph[nextX][nextY]) {
                    continue; // 当前遍历节点比下一选择节点要小，不满足流向规则
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

    /**
     * 校验节点是否可以到达边界（第1组边界、第2组边界）
     */
    public static boolean vaildPair(int[][] graph, int x, int y) {
        int n = graph.length, m = graph[0].length;
        boolean[][] visited = new boolean[n][m]; // 每次检索重置遍历矩阵
        // 对当前节点进行bfs操作
        bfs(graph, visited, x, y);

        // 根据当前搜索的结果，判断当前已遍历的节点是否触达边界
        boolean firstBorder = false, secondBorder = false;
        // 按行判断左右边界是否触达
        for (int i = 0; i < n; i++) {
            if (visited[i][0]) {
                firstBorder = true; // 第1组边界（左）
                break;// 只要找到一个触达边界即可满足
            }
        }
        for (int i = 0; i < n; i++) {
            if (visited[i][m - 1]) {
                secondBorder = true; // 第二组边界（右）
                break;
            }
        }

        // 按列判断上下边界是否触达
        for (int j = 0; j < m; j++) {
            if (visited[0][j]) {
                firstBorder = true; // 第1组边界（上）
                break;
            }
        }
        for (int j = 0; j < m; j++) {
            if (visited[n - 1][j]) {
                secondBorder = true; // 第2组边界（下）
                break;
            }
        }

        // 如果两个边界均可触达，则当前路径有效
        return firstBorder && secondBorder;
    }

    public static void main(String[] args) {

        // 1.输入控制
        int[][] graph = {
                {1, 3, 1, 2, 4}, {1, 2, 1, 3, 2}, {2, 4, 7, 2, 1},
                {4, 5, 6, 1, 1,}, {1, 4, 1, 2, 1}
        };
        int n = graph.length, m = graph[0].length;

        // 2.判断每个节点是否可以同时到达第1组边界和第2组边界，如果可以则输出
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (vaildPair(graph, i, j)) {
                    System.out.println("i:" + i + "  j:" + j);
                }
            }
        }

    }

}
