package com.noob.algorithm.solution_archive.dmsxl.kmw.q102;

import com.noob.algorithm.solution_archive.dmsxl.graph.Pair;
import com.noob.algorithm.solution_archive.dmsxl.util.PrintUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 102 沉没孤岛（更新地图 BFS）
 */
public class Solution2 {

    static int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 定义遍历的4个方向加成（往4个方向进行检索）右、下、左、上

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
        graph[x][y] = 2; // 更新地图（将陆地进行标记，标记为2）

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
                    graph[nextX][nextY] = 2; // 更新地图（将陆地进行标记，标记为2）
                }
            }
        }
    }

    public static void main(String[] args) {

        // 1.输入控制（邻接矩阵处理）
//         int[][] graph = GraphInputUtil.getMatrixGraph(0);
        int[][] graph = new int[][]{
                {0, 1, 0, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}, {0, 1, 1, 1, 0, 1, 1, 1}, {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 1, 0, 0, 1, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0}, {0, 1, 1, 0, 0, 1, 1, 0}
        };

        int n = graph.length, m = graph[0].length;

        // 2.更新地图：分别从周边出发向中间进行搜索（将与边缘接触的陆地变为海洋） （步骤①）
        // 分别从左侧、右侧向中间遍历，更新地图
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

        // 分别从上侧、下侧向中间遍历，更新地图
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

        // 3.根据标记重置地图
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 1) {
                    graph[i][j] = 0; // 经过上述地图更新，此处剩余的陆地均为孤岛范畴，可以直接将孤岛沉没 （步骤②）
                }
                if (graph[i][j] == 2) {
                    graph[i][j] = 1; // 经过上述地图更新，被标记为2的地域是原来与边缘相接的陆地，将其重置为1（步骤③）
                }
            }
        }

        // 4.输出最终更新的地图（沉没孤岛）
        System.out.println("----------沉没孤岛后的更新地图----------");
        PrintUtil.printGraphMatrix(graph);
    }
}
