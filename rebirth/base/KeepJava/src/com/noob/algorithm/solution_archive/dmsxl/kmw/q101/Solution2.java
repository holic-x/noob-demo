package com.noob.algorithm.solution_archive.dmsxl.kmw.q101;

import com.noob.algorithm.solution_archive.dmsxl.graph.GraphInputUtil;

import java.util.Scanner;

/**
 * 100 岛屿的最大面积（标记处理 DFS）
 */
public class Solution2 {

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
        // 递归出口
        if (visited[x][y] || graph[x][y] == 0) {
            // 如果当前节点已经被遍历过，或者为非陆地则退出
            return;
        }

        // 遍历当前节点
        visited[x][y] = true;
        area++; // 岛屿面积+1
        // 设置孤岛标识（判断节点是否在边缘）
        if (x == 0 || x == n - 1 || y == 0 || y == m - 1) {
            islandFlag = false;
        }

        // 递归处理（往4个方向进行检索，此处选择列表为4个方向，遇到边界可跳过）
        for (int i = 0; i < 4; i++) {
            // 计算下一个要选择遍历的坐标
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            // 判断坐标是否越界（x∈[0,graph.length),y∈[0,graph[0].length)）
            if (nextX < 0 || nextX >= graph.length || nextY < 0 || nextY >= graph[0].length) {
                continue; // 坐标越界，跳过当前选择
            }
            // 判断条件放在了方法首部，此处直接调用dfs方法
            dfs(graph, visited, nextX, nextY);
        }
    }

    // 输入控制，封装邻接矩阵
    public static int[][] getGraph() {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入整数N（矩阵行）、M（矩阵列）");
        String[] nm = sc.nextLine().trim().split("\\s+");
        int n = Integer.valueOf(nm[0]), m = Integer.valueOf(nm[1]);
        // 定义邻接矩阵
        int[][] graph = new int[n][m];

        System.out.println("输入N行，每行包含M个数字（数字为1或0）");
        for (int i = 0; i < n; i++) {
            String[] input = sc.nextLine().trim().split("\\s+");
            for (int j = 0; j < input.length; j++) {
                graph[i][j] = Integer.valueOf(input[j]);
            }
        }
        return graph;
    }

    public static void main(String[] args) {

        // 1.输入控制（邻接矩阵处理）
         int[][] graph = GraphInputUtil.getMatrixGraph(0);
//        int[][] graph = new int[][]{
//                {0, 1, 0, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}, {0, 1, 1, 1, 0, 1, 1, 1}, {0, 0, 0, 0, 1, 0, 0, 0},
//                {0, 1, 0, 0, 1, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0}, {0, 1, 1, 0, 0, 1, 1, 0}
//        };

        int n = graph.length, m = graph[0].length;

        // 定义visited数组，记录已遍历的节点
        boolean[][] visited = new boolean[n][m]; // 初始化默认为false

        // 2.调用方法获取岛屿数量（遍历每一个可能的起点）
        int res = 0; // 孤岛面积统计
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                area = 0; // 对于每一个可能的岛屿起点遍历，初始化area
                islandFlag = true; // 对于每一个可能的岛屿起点遍历，初始化islandFlag为true（默认为孤岛，如果遇到边界则说明非孤岛）
                if (!visited[i][j] && graph[i][j] == 1) { // 当前节点没有被遍历过且为陆地，则以该点为起点进行dfs
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
