package com.noob.algorithm.solution_archive.dmsxl.graph.minShortPath.dijkstra;

import com.noob.algorithm.solution_archive.dmsxl.graph.Edge;

import java.util.*;

/**
 * dijkstra 迪杰斯特拉算法（邻接表处理方式）
 */
public class DijkstraTemplate1 {

    public static void dijkstra(int n, List<Edge> edges, int startIdx) {
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

        // 构建每个节点关联的节点列表（节点i可到达的其他节点列表）
        List<List<Integer>> links = new ArrayList<>();
        for (int i = 0; i <= n; i++) { // 初始化（此处要将0位置也空出）
            links.add(new ArrayList<>());
        }
        // 构建每个节点边路径和权值的映射关系
        Map<String, Integer> map = new HashMap<>();
        // 根据edges边关系构建
        for (Edge edge : edges) {
            int u = edge.u;
            int v = edge.v;
            links.get(u).add(v);
            map.put(u + "->" + v, edge.val);
        }

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
            List<Integer> relateNodes = links.get(cur);
            for (int v : relateNodes) {
                if (!selected[v]) {
                    minDist[v] = Math.min(minDist[v], minDist[cur] + map.get(cur + "->" + v));
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
        List<Edge> edges = new ArrayList<>();
        while (m-- > 0) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();
            edges.add(new Edge(u, v, weight));
        }

        // 调用算法
        DijkstraTemplate1 dijkstraTemplate = new DijkstraTemplate1();
        dijkstraTemplate.dijkstra(n, edges, 1);
    }
}
