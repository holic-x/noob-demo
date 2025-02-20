package com.noob.algorithm.solution_archive.dmsxl.graph.minShortPath.dijkstra;

import com.noob.algorithm.solution_archive.dmsxl.graph.Edge;

import java.util.*;

/**
 * dijkstra 迪杰斯特拉算法（邻接表处理方式）
 */
public class DijkstraTemplate4 {

    /**
     * @param n        节点个数
     * @param graph    邻接表（图）
     * @param startIdx 源点
     */
    public static void dijkstra(int n, List<List<Edge>> graph, int startIdx) {
        int maxValue = Integer.MAX_VALUE;

        // 构建minDist[]: 源点到指定节点的最短距离
        int[] minDist = new int[n + 1]; // 此处节点有效范围选择[1,n]
        for (int i = 0; i <= n; i++) {
            // 初始化（此处根据源点校验，源点到源点自身的距离设置为0）
            if (i == startIdx) {
                minDist[i] = 0; // 源点到自身的距离为0
            } else {
                minDist[i] = maxValue; // 其他节点的最短距离默认设置为范围内的最大值
            }
        }

        // 构建遍历标识
        boolean[] selected = new boolean[n + 1]; // 节点有效范围选择[1,n]
        Arrays.fill(selected, false); // 初始化设置为未被遍历过（未被选择）

        // 主循环（dijkstra算法核心：dijkstra三部曲）
        for (int cnt = 1; cnt <= n; cnt++) {
            // 1.选择距离源点的最近节点
            int cur = -1; // 定义选择指针
            int minVal = maxValue; // 如果minDist全初始化为maxValue，则此处取maxValue + 1确保循环正常进入处理
            for (int i = 1; i <= n; i++) {
                if (!selected[i] && minDist[i] < minVal) { // 从未遍历节点中选择
                    cur = i;
                    minVal = minDist[i];
                }
            }

            // 2.将这个最近节点标记为已被选择（遍历）
            selected[cur] = true;

            // 3.更新经由当前选择节点扩展的新路径（cur可到达的其他节点）
            List<Edge> relateEdges = graph.get(cur);
            for (Edge edge : relateEdges) {
                if (!selected[edge.v]) {
                    minDist[edge.v] = Math.min(minDist[edge.v], minDist[cur] + edge.val);
                }
            }
        }

        // 处理结果：输出结果
        for (int i = 1; i <= n; i++) {
            System.out.println(minDist[i]);
        }
    }

    public static void main(String[] args) {
        // 输入控制
        Scanner sc = new Scanner(System.in);
        System.out.println("1.输入N（节点个数）M边数");
        int n = sc.nextInt();
        int m = sc.nextInt();
        System.out.println("2.输入边");
        // 定义邻接表（表示每个节点关联的边(可以从边定义中跟踪到(u,v,w)关系)）
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            // 初始化邻接表：下标索引对应为节点u(取值范围：[1,n]),下标对应元素值为节点u关联的边关系
            graph.add(new ArrayList<>());
        }
        // 处理边关系
        while (m-- > 0) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();
            graph.get(u).add(new Edge(u, v, weight));
        }

        // 调用算法
        DijkstraTemplate4 dijkstraTemplate = new DijkstraTemplate4();
        dijkstraTemplate.dijkstra(n, graph, 1);
    }
}


