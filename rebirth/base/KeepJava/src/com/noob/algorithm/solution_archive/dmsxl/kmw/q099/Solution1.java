package com.noob.algorithm.solution_archive.dmsxl.kmw.q099;

import java.util.Scanner;

/**
 * 099 岛屿数量
 */
public class Solution1 {

    static int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 定义遍历的4个方向加成（往4个方向进行检索）右、下、左、上

    /**
     * DFS
     *
     * @param graph   邻接矩阵
     * @param visited 遍历标记（如果已遍历的元素则进行标记）
     * @param x       当前遍历坐标x
     * @param y       当前遍历坐标y
     */
    public static void dfs(int[][] graph, boolean[][] visited, int x, int y) {

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
                dfs(graph, visited, nextX, nextY);
            }
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
        int[][] graph = getGraph();
        int n = graph.length, m = graph[0].length;

        // 定义visited数组，记录已遍历的节点
        boolean[][] visited = new boolean[n][m]; // 初始化默认为false

        // 2.调用方法获取岛屿数量（遍历每一个可能的起点）
        int cnt = 0; // 岛屿数量
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && graph[i][j] == 1) { // 当前节点没有被遍历过且为陆地，则以该点为起点进行dfs
                    visited[i][j] = true; // 将当前节点标记为已遍历
                    cnt++;
                    dfs(graph, visited, i, j); // 递归检索
                }
            }
        }

        // 返回结果
        System.out.println("岛屿数量：" + cnt);
    }


}
