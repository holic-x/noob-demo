package com.noob.algorithm.dmsxl.kmw.q101;

import com.noob.algorithm.dmsxl.graph.GraphInputUtil;
import com.noob.algorithm.dmsxl.graph.Pair;
import com.noob.algorithm.dmsxl.util.PrintUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 101 孤岛总面积（更新地图 BFS）
 */
public class Solution4 {
    static int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 定义遍历的4个方向加成（往4个方向进行检索）右、下、左、上

    static int area = 0; // 当前遍历岛屿面积

    /**
     * BFS
     *
     * @param graph 邻接矩阵
     * @param x     当前遍历坐标x
     * @param y     当前遍历坐标y
     */
    public static void bfs(int[][] graph, int x, int y) {
        // 构建辅助队列
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(x, y)); // 初始化队列
        graph[x][y] = 0; // 只要加入队列就立刻进行标记(更新地图，将陆地变为海洋)
        area++; // 岛屿面积+1

        // 队列不为空时进行遍历
        while (!queue.isEmpty()) {
            // 取出当前节点
            Pair curPair = queue.poll();
            int curX = curPair.x;
            int curY = curPair.y;

            // 往四个方向进行遍历
            for (int i = 0; i < 4; i++) {
                int nextX = curX + dir[i][0];
                int nextY = curY + dir[i][1];
                // 判断节点是否超出边界，如果超界则跳过
                if (nextX < 0 || nextX >= graph.length || nextY < 0 || nextY >= graph[0].length) {
                    continue;
                }
                // 将节点加入队列（在加入队列的同时更新地图（将陆地变为海洋））
                if (graph[nextX][nextY] == 1) {
                    queue.offer(new Pair(nextX, nextY));
                    graph[nextX][nextY] = 0; // 只要加入队列就立刻进行标记(更新地图，将陆地变为海洋)
                    area++; // 岛屿面积+1
                }
            }
        }
        // System.out.println("BFS操作渲染完成");
    }

    public static void main(String[] args) {

        // 1.输入控制（邻接矩阵处理）
//         int[][] graph = GraphInputUtil.getMatrixGraph(0);
        int[][] graph = new int[][]{
                {0, 1, 0, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}, {0, 1, 1, 1, 0, 1, 1, 1}, {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 1, 0, 0, 1, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0}, {0, 1, 1, 0, 0, 1, 1, 0}
        };

        int n = graph.length, m = graph[0].length;

        // 2.调用方法获取岛屿数量（遍历每一个可能的起点）
        // 从四周边缘向中间进行广搜，将边缘周边的陆地连接的陆地全部置为海洋
        for (int i = 0; i < n; i++) {
            // 如果边缘关联陆地，则进行搜索将其关联陆地全部置为海洋
            if (graph[i][0] == 1) { // 左侧边缘判断
                bfs(graph, i, 0);
            }

            if (graph[i][m - 1] == 1) { // 右侧边缘判断
                bfs(graph, i, m - 1);
            }

        }
        System.out.println("----------左右侧向中间搜索将边缘连接陆地置为海洋----------");
        PrintUtil.printGraphMatrix(graph); // 打印处理

        for (int j = 0; j < m; j++) {
            // 如果边缘关联陆地，则进行搜索将其关联陆地全部置为海洋
            if (graph[0][j] == 1) { // 上方边缘判断
                bfs(graph, 0, j);
            }

            if (graph[n - 1][j] == 1) { // 下方边缘判断
                bfs(graph, n - 1, j);
            }
        }
        System.out.println("----------上下两侧向中间搜索将边缘连接陆地置为海洋----------");
        PrintUtil.printGraphMatrix(graph); // 打印处理

        // 重置岛屿面积计数器(经过上述操作渲染，最终地图中留存的是剩下的孤岛，正常遍历计算岛屿面积)
        area = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 1) { // 当前节点没有被遍历过且为陆地，则以该点为起点进行dfs
                    bfs(graph, i, j); // 广度检索：将与其连接的陆地都标记上true
                }
            }
        }

        // 返回结果
        System.out.println("孤岛岛屿面积：" + area);
    }
}
