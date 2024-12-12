package com.noob.algorithm.dmsxl.kmw.q099;

import com.noob.algorithm.dmsxl.graph.Pair;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 099 岛屿数量
 */
public class Solution3 {

    static int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 定义遍历的4个方向加成（往4个方向进行检索）右、下、左、上

    /**
     * BFS
     *
     * @param graph   邻接矩阵
     * @param visited 遍历标记（如果已遍历的元素则进行标记）
     * @param x       当前遍历坐标x
     * @param y       当前遍历坐标y
     */
    public static void bfs(int[][] graph, boolean[][] visited, int x, int y) {
        // 构建辅助队列
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(x, y)); // 初始化队列
        visited[x][y] = true; // 只要加入队列就立刻进行标记（避免重复遍历标记的情况）

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
                // 将节点加入队列（在加入队列的同时标记该节点的遍历状态）
                if (!visited[nextX][nextY] && graph[nextX][nextY] == 1) {
                    queue.offer(new Pair(nextX, nextY));
                    visited[nextX][nextY] = true; // 只要加入队列就立刻进行标记（避免重复遍历标记的情况）
                }
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
                    cnt++;
                    bfs(graph, visited, i, j); // 广度检索：将与其连接的陆地都标记上true
                }
            }
        }

        // 返回结果
        System.out.println("岛屿数量：" + cnt);
    }
}
