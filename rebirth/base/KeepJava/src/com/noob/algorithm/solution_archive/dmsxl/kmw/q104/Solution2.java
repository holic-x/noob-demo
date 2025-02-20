package com.noob.algorithm.solution_archive.dmsxl.kmw.q104;


import java.util.HashMap;
import java.util.Map;

/**
 * 104 建造最大人工岛
 */
public class Solution2 {

    static int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 定义4个遍历方向（x、y坐标位移值）
    static int area = 0; // 当前搜索岛屿面积值
    static int mark = 2; // 岛屿编号标记(从2开始计数)

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
        graph[x][y] = mark; // 记录岛屿标记

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

    // 获取当前地图的岛屿面积map（Map<岛屿编号,岛屿面积>）
    public static Map<Integer, Integer> geIslandArea(int[][] graph) {
        Map<Integer, Integer> map = new HashMap<>();

        int n = graph.length, m = graph[0].length;

        boolean[][] visited = new boolean[n][m]; // 标记遍历节点，避免重复标记
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 1) { // 此处需限定为遍历未遍历节点，确保mark计数正确
                    area = 0; // 此处遍历每个可能的岛屿起点，已经遍历的元素不会重复遍历
                    // 递归搜索，获取当前岛屿面积
                    dfs(graph, visited, i, j);
                    // 记录岛屿编号和岛屿面积映射关系
                    map.put(mark, area);
                    mark++; // 更新下一个岛屿编号
                }
            }
        }
        return map;
    }


    public static void main(String[] args) {
        // 1.输入控制
        // int[][] graph = GraphInputUtil.getMatrixGraph(0);
        int[][] graph = new int[][]{
                {0, 1, 0, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}, {0, 1, 1, 1, 0, 1, 1, 1}, {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0}, {0, 1, 1, 0, 0, 1, 1, 0}
        };

        int n = graph.length, m = graph[0].length;

        // 2.获取当前地图的每个岛屿的编号和面积
        Map<Integer, Integer> map = geIslandArea(graph);

        // 3.遍历每个"海域"，判断其邻接的4个区域是否为归属岛屿（标记>=2表示被划分为某个岛屿），对当前海域节点进行改造则需累加周边岛屿面积
        int maxArea = 0;
        // 从当前岛屿列表中更新最大岛屿面积
        for (int key : map.keySet()) {
            maxArea = Math.max(maxArea, map.get(key));
        }
        // 遍历每个海域，确认改造后的最大岛屿面积
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 最多可以改造一次，因此可以选择改造或者不改造，此处如果为海洋则进行改造
                if (graph[i][j] == 0) {
                    System.out.println("-------------------改造 start ------------------------");

                    System.out.println("当前改造海域节点：i-" + i + "\tj-" + j);
                    int curArea = 1;
                    // 判断当前海域节点邻接的4个区域是否归属岛屿,累加邻接岛屿面积
                    for (int k = 0; k < 4; k++) {
                        int nextX = i + dir[k][0];
                        int nextY = j + dir[k][1];
                        System.out.println("邻接节点：i-" + nextX + "\tj-" + nextY);
                        // 判断节点是否越界，越界则跳过
                        if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) {
                            System.out.println("warning：当前方向邻接节点越界");
                            continue;
                        }
                        System.out.println("当前邻接节点：i-" + nextX + "\tj-" + nextY + "归属岛屿编号" + graph[nextX][nextY]);
                        // 判断邻接节点是否归属岛屿，累加
                        curArea += map.getOrDefault(graph[nextX][nextY], 0);
                        // 更新最大面积
                        maxArea = Math.max(maxArea, curArea);
                    }
                    System.out.println("-------------------改造 end ------------------------");
                }
            }
        }
        System.out.println("改造后的最大岛屿面积：" + maxArea);
    }

}
