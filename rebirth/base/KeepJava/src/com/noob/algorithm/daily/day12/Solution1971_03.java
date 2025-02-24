package com.noob.algorithm.daily.day12;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 🟢 1971 - 寻找图中是否存在路径
 */
public class Solution1971_03 {

    /**
     * 思路分析：BFS 搜索思路
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

        // 构建辅助队列
        Queue<Integer> queue = new LinkedList<>();
        // 初始化队列
        queue.offer(source); // 从source节点出发
        visited[source] = true;

        // 遍历队列
        while (!queue.isEmpty()) {
            // 取出节点
            int u = queue.poll();
            // 根据边关系确定可达路径（获取当前节点连接的下一个节点）
            for (int next : graph.get(u)) {
                // 将该节点加入队列（如果节点还没遍历）
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true; // 处理节点(标记为已遍历) - 只要一加入队列就同步更新遍历状态
                }
            }
        }

        // 确认从source出发的节点进行遍历，看是否可以到达dest
        return visited[destination];
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}};
        Solution1971_03 s = new Solution1971_03();
        System.out.println(s.validPath(6, edges, 0, 5));
    }
}
