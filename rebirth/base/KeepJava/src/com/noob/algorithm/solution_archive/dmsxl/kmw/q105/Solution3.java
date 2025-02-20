package com.noob.algorithm.solution_archive.dmsxl.kmw.q105;

import com.noob.algorithm.solution_archive.dmsxl.graph.GraphInputUtil;
import com.noob.algorithm.solution_archive.dmsxl.util.PrintUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * KMW105-有向图的完全可达性
 */
public class Solution3 {

    // bfs 版本
    public static void bfs(List<List<Integer>> graph, boolean[] visited, int key) {

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(key); // 初始化队列，元素一入队就标记
        visited[key] = true;

        // 遍历队列，搜索节点
        while (!queue.isEmpty()) {
            // 取出元素
            int cur = queue.poll();
            // 继续搜索元素的邻接节点
            for (int nextKey : graph.get(cur)) {
                // 如果元素未被遍历，则进行标记
                if (!visited[nextKey]) {
                    queue.offer(nextKey); // 节点入队
                    visited[nextKey] = true; // 元素一入队就标记
                }
            }
        }
    }

    public static void main(String[] args) {
        // 1.输入控制（输入邻接表）
        List<List<Integer>> graph = GraphInputUtil.getTableGraph(0); // 1 表示手动输入控制
        PrintUtil.printGraphTable(graph);

        // 2.dfs 检索有向图
        boolean[] visited = new boolean[graph.size()]; // 存储已遍历节点(graph存储处理范围为[0,n],即graph.size()为n个节点，此处正常处理即可)
        bfs(graph, visited, 1); // 判断从1号节点出发是否可以到达其他所有节点

        // 3.校验visited标记（判断节点1出发是否可以到达其他节点）
        for (int i = 1; i < graph.size(); i++) { // 节点编号范围[1,graph.size()) (graph.size()的取值为n+1)
            if (!visited[i]) {
                System.out.println("-1" + "不可达");
                return;
            }
        }
        System.out.println("1" + "可达");
    }
}
