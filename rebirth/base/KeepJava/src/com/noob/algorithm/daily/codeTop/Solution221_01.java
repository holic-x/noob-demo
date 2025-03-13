package com.noob.algorithm.daily.codeTop;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟡 221 最大正方形 - https://leetcode.cn/problems/maximal-square/description/
 *
 */
public class Solution221_01 {

    // 定义四个方向的遍历索引
    int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    // 定义当前遍历岛屿面积
    int curArea = 0;

    // ❌ 思路错误，此处不应该用岛屿方式求解，因为岛屿的面积是不规则的，需要排除"非正方形"的岛屿情况
    // 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积
    public int maximalSquare(char[][] matrix) {
        // 遍历每个节点，如果节点为`1` 且没有被访问过，则基于该点进行bfs渲染检索
        int m = matrix.length, n = matrix[0].length;
        // 初始化标记数组
        boolean[][] visited = new boolean[m][n]; // 默认初始化均为false

        int maxArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1' && !visited[i][j]) {
                    // 重置面积计数器
                    curArea = 0;
                    bfs(matrix, visited, i, j);
                    maxArea = Math.max(maxArea, curArea);
                }
            }
        }
    }

    /**
     * bfs 检索
     *
     * @param matrix  原数组
     * @param visited 标记数组中为`1`的区域是否已经被访问
     * @param x       当前x坐标
     * @param y       当前y坐标
     */
    private void bfs(char[][] matrix, boolean[][] visited, int x, int y) {
        int m = matrix.length, n = matrix[0].length;
        // 定义队列辅助遍历
        Queue<Pair> queue = new LinkedList<>();
        // 初始化入队（当前遍历位置没有被访问过，且为‘1’）
        queue.offer(new Pair(x, y));
        visited[x][y] = true; // 标记当前节点
        curArea++;

        // 分别向4个方向检索
        for (int i = 0; i < 4; i++) {
            int nextX = x + dirs[i][0];
            int nextY = y + dirs[i][1];
            // 校验(nextX\nextY坐标是否越界，且是否为‘1’，是否已经被访问过)
            if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || matrix[nextX][nextY] != '1' || visited[nextX][nextY]) {
                continue; // 跳过当前节点
            }
            // 满足条件，加入队列等待访问
            queue.offer(new Pair(nextX, nextY));
            visited[nextX][nextY] = true; // 标记当前节点
            curArea++;
        }
    }

    // 自定义坐标实体
    class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


}
