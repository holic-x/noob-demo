package com.noob.algorithm.dmsxl.kmw.q101;

import com.noob.algorithm.dmsxl.graph.GraphInputUtil;


/**
 * 101 孤岛总面积（标记处理 DFS）
 */
public class Solution1 {

    static int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 定义遍历的4个方向加成（往4个方向进行检索）右、下、左、上

    static int area = 0; // 当前遍历岛屿面积
    static boolean islandFlag = true; // 孤岛标识（true表示为孤岛）

    /**
     * DFS
     *
     * @param graph   邻接矩阵
     * @param visited 遍历标记（如果已遍历的元素则进行标记）
     * @param x       当前遍历坐标x
     * @param y       当前遍历坐标y
     */
    public static void dfs(int[][] graph, boolean[][] visited, int x, int y) {
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
            // 递归处理(如果当前选择节点没有被遍历过，且为陆地，则将其置为true并递归检索下一个连接的陆地)
            if (!visited[nextX][nextY] && graph[nextX][nextY] == 1) {
                visited[nextX][nextY] = true;
                area++; // 匹配，当前岛屿面积+1
                // 设置孤岛标识（判断节点是否在边缘）
                if (nextX == 0 || nextX == n - 1 || nextY == 0 || nextY == m - 1) {
                    islandFlag = false;
                }
                dfs(graph, visited, nextX, nextY);
            }
        }
    }


    public static void main(String[] args) {

        // 1.输入控制（邻接矩阵处理）
        int[][] graph = GraphInputUtil.getMatrixGraph(0);
//        int[][] graph = new int[][]{
//                {0,1,0,0,0,0,0,0},{1,1,1,0,0,0,1,1},{0,1,1,1,0,1,1,1},{0,0,0,0,1,0,0,0},
//                {0,1,0,0,1,0,0,0},{0,0,1,0,0,0,0,0},{0,1,1,0,0,1,1,0}
//        };

        int n = graph.length, m = graph[0].length;

        // 定义visited数组，记录已遍历的节点
        boolean[][] visited = new boolean[n][m]; // 初始化默认为false

        // 2.调用方法获取岛屿数量（遍历每一个可能的起点）
        int res = 0; // 孤岛面积统计
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                area = 0; // 每遍历一个可能起点，面积重置
                islandFlag = true; // 对于每一个可能的岛屿起点遍历，初始化islandFlag为true（默认为孤岛，如果遇到边界则说明非孤岛）
                if (!visited[i][j] && graph[i][j] == 1) { // 当前节点没有被遍历过且为陆地，则以该点为起点进行dfs
                    visited[i][j] = true; // 将当前节点标记为已遍历
                    area++;
                    // 设置孤岛标识（判断节点是否在边缘）
                    if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                        islandFlag = false;
                    }
                    dfs(graph, visited, i, j); // 递归检索
                }
                // 更新孤岛的面积和
                res += (islandFlag) ? area : 0; // 如果是孤岛则累加孤岛面积
            }
        }

        // 返回结果
        System.out.println("孤岛岛屿面积：" + res);
    }
}
