package com.noob.algorithm.dmsxl.graph.graphSearch;

import com.noob.algorithm.dmsxl.graph.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 图 遍历
 * 图论：广度优先遍历代码模板
 */
public class BFSTemplate {
    // int[4][2]
    int[][] dir = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}}; // 表示4个方向

    /**
     * @param graph   图（邻接矩阵）
     * @param visited 用于标记已访问过的节点（不能重复访问）
     * @param x,y     表示开始搜索的节点下标
     */
    void bfs(int[][] graph, boolean[][] visited, int x, int y) {
        // 定义队列
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(x, y)); // 初始化
        visited[x][y] = true; // 入队的同时记录为已遍历，避免重复访问
        // 队列不为空，进行遍历
        while (!queue.isEmpty()) {
            Pair curPair = queue.poll();
            int curX = curPair.x; // 横坐标
            int curY = curPair.y; // 纵坐标
            for (int i = 0; i < 4; i++) { // 从当前节点的4个方向左右上下去遍历
                // 顺时针遍历新节点next，下面记录坐标
                int nextX = curX + dir[i][0];
                int nextY = curY + dir[i][1];
                if (nextX < 0 || nextX >= graph.length || nextY < 0 || nextY >= graph[0].length) {
                    continue; // 去除越界部分（坐标越界则直接跳过）
                }
                // 如果节点没有被访问过，则添加该节点为下一轮要遍历的节点，并在入队时标记为已遍历
                if (!visited[nextX][nextY] && graph[nextX][nextY] == 1) {
                    queue.add(new Pair(nextX, nextY));
                    visited[nextX][nextY] = true;// 逻辑同上
                }
            }
        }
    }
}

