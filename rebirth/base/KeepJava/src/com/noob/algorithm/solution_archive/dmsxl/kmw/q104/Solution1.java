package com.noob.algorithm.solution_archive.dmsxl.kmw.q104;

import com.noob.algorithm.solution_archive.dmsxl.graph.GraphInputUtil;
import com.noob.algorithm.solution_archive.dmsxl.graph.Pair;
import com.noob.algorithm.solution_archive.dmsxl.util.PrintUtil;

/**
 * 104 建造最大人工岛
 */
public class Solution1 {

    static int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 定义4个遍历方向（x、y坐标位移值）
    static int area = 0; // 当前搜索岛屿面积值

    /**
     * 定义dfs检索
     *
     * @param graph   邻接矩阵
     * @param visited 遍历标记
     * @param x,y     当前遍历节点坐标
     */
    public static void dfs(int[][] graph, boolean[][] visited, int x, int y) {
        // 递归出口校验（如果已遍历过或非陆地则return）
        if (visited[x][y] || graph[x][y] == 0) {
            return;
        }

        visited[x][y] = true; // 标记当前节点为已遍历
        area++; // 面积累加

        int n = graph.length, m = graph[0].length;
        // 从4个方向搜索，递归判断下一个可选择节点
        for (int i = 0; i < 4; i++) {
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            // 校验节点是否越界，越界则跳过
            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) {
                continue; // 越界
            }
            // 递归处理(在递归方法前置处理递归出口，此处直接调用递归方法即可)
            dfs(graph, visited, nextX, nextY);
        }
    }

    // 获取当前地图的最大岛屿面积
    public static int getMaxIslandArea(int[][] graph){
        int n = graph.length, m = graph[0].length;

        int maxArea = 0; // 记录最大岛屿值
        boolean[][] visited = new boolean[n][m]; // 标记遍历节点，避免重复标记
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                area = 0; // 此处遍历每个可能的岛屿起点，已经遍历的元素不会重复遍历
                // 递归搜索，获取当前岛屿面积
                dfs(graph, visited, i, j);
                maxArea = Math.max(maxArea, area); // 更新岛屿的最大面积
            }
        }
        return maxArea;
    }


    public static void main(String[] args) {
        // 1.输入控制
        int[][] graph = GraphInputUtil.getMatrixGraph(0);
        int n = graph.length, m = graph[0].length;

        // 2.遍历每个节点，将"海洋"改造为"陆地"，并计算当前地图更新后的最大岛屿值
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 最多可以改造一次，因此可以选择改造或者不改造，此处如果为海洋则进行改造
                if (graph[i][j] == 0) {
                    System.out.println("------------------------------start--------------------------------");
                    System.out.println("执行改造计划：" + "i:" + i + " j:" + j + "   更新后的地图：");
                    graph[i][j] = 1; // 执行改造计划，更新地图
                    maxArea = Math.max(maxArea, getMaxIslandArea(graph)); // 获取地图更新后的最大岛屿面积
                    PrintUtil.printGraphMatrix(graph);
                    graph[i][j] = 0; // 恢复现场（回撤改造计划，继续遍历下一个节点）
                    System.out.println("------------------------------end--------------------------------");
                }else{
                    // 获取不改造该节点时的最大岛屿面积
                    maxArea = Math.max(maxArea, getMaxIslandArea(graph)); // 获取地图更新后的最大岛屿面积
                }
            }
        }
        System.out.println("改造后的最大岛屿面积：" + maxArea);
    }

}
