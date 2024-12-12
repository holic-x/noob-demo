package com.noob.algorithm.dmsxl.kmw.q102;

import com.noob.algorithm.dmsxl.util.PrintUtil;

/**
 * 102 沉没孤岛（更新地图 DFS）
 */
public class Solution1 {

    static int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 定义遍历的4个方向加成（往4个方向进行检索）右、下、左、上

    /**
     * DFS
     *
     * @param graph 邻接矩阵
     * @param x     当前遍历坐标x
     * @param y     当前遍历坐标y
     */
    public static void dfs(int[][] graph, int x, int y) {
        // 递归出口
        if (graph[x][y] != 1) {
            return;
        }

        graph[x][y] = 2; // 更新地图（将陆地进行标记，标记为2）

        int n = graph.length, m = graph[0].length;
        // 递归处理（往4个方向进行检索，此处选择列表为4个方向，遇到边界可跳过）
        for (int i = 0; i < 4; i++) {
            // 计算下一个要选择遍历的坐标
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            // 判断坐标是否越界（x∈[0,graph.length),y∈[0,graph[0].length)）
            if (nextX < 0 || nextX >= graph.length || nextY < 0 || nextY >= graph[0].length) {
                continue; // 坐标越界，跳过当前选择
            }
            // 递归处理: 此处在递归出口进行条件控制，直接调用递归方法
            dfs(graph, nextX, nextY);
        }
    }


    public static void main(String[] args) {

        // 1.输入控制（邻接矩阵处理）
//        int[][] graph = GraphInputUtil.getMatrixGraph(0);
        int[][] graph = new int[][]{
                {0, 1, 0, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}, {0, 1, 1, 1, 0, 1, 1, 1}, {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 1, 0, 0, 1, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0}, {0, 1, 1, 0, 0, 1, 1, 0}
        };

        int n = graph.length, m = graph[0].length;

        // 2.更新地图：分别从周边出发向中间进行搜索（将与边缘接触的陆地变为海洋） （步骤①）
        // 分别从左侧、右侧向中间遍历，更新地图
        for (int i = 0; i < n; i++) {
            if (graph[i][0] == 1) {
                dfs(graph, i, 0);
            }
            if (graph[i][n - 1] == 1) {
                dfs(graph, i, n - 1);
            }
        }

        // 分别从上侧、下侧向中间遍历，更新地图
        for (int j = 0; j < m; j++) {
            if (graph[0][j] == 1) {
                dfs(graph, 0, j);
            }
            if (graph[n - 1][j] == 1) {
                dfs(graph, n - 1, j);
            }
        }

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
        PrintUtil.printGraphMatrix(graph);
    }
}
