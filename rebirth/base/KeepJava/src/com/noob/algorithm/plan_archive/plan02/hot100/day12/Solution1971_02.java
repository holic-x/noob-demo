package com.noob.algorithm.plan_archive.plan02.hot100.day12;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 🟢 1971 - 寻找图中是否存在路径
 */
public class Solution1971_02 {

    /**
     * 思路分析：DFS 搜索思路
     */
    public boolean validPath(int n, int[][] edges, int source, int destination) {

        // 处理边(构建图：邻接矩阵)
        List<List<Integer>> graph = new ArrayList<>();
        // 初始化
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // 遍历边
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v); // 处理u->v
            graph.get(v).add(u); // 处理v->u
        }

        // 定义节点遍历标识
        boolean[] visited = new boolean[n];

        // 调用DFS进行搜索(确认从source出发的节点进行遍历，看是否可以到达dest)
        return dfs(graph, visited, source,destination);
        // return visited[destination];
    }

    // DFS
    private boolean dfs(List<List<Integer>> graph, boolean[] visited, int source, int dest) {
        if (source == dest) {
            return true;
        }

        // 标记节点
        visited[source] = true;

        // 检索u关联的节点
        for (int next : graph.get(source)) {
            if (!visited[next] && dfs(graph, visited, next, dest)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}};
        Solution1971_02 s = new Solution1971_02();
        System.out.println(s.validPath(6, edges, 0, 5));
    }
}
