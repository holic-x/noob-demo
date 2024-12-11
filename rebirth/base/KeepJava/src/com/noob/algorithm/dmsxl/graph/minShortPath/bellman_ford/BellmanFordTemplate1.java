package com.noob.algorithm.dmsxl.graph.minShortPath.bellman_ford;

import com.noob.algorithm.dmsxl.graph.Edge;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * bellmanFord算法(处理带负权值的有向图的最短路径：起点到终点)
 * 邻接表处理方式
 */
public class BellmanFordTemplate1 {

    // bellmanFord算法(处理带负权值的有向图的最短路径：起点到终点)
    public static void bellmanFord(int n, List<List<Edge>> graph, int startIdx) {
        // 定义最大范围
        int maxVal = Integer.MAX_VALUE;
        // minDist[i] 源点到节点i的最短距离
        int[] minDist = new int[n + 1]; // 有效节点编号范围：[1,n]
        for (int i = 1; i <= n; i++) {
            if (i == startIdx) {
                minDist[i] = 0;
            } else {
                minDist[i] = maxVal;
            }
        }

        // bellmanFord 算法核心:对所有边松弛n-1次
        for (int i = 1; i < n; i++) {
            // 执行bellmanFord核心，遍历所有边
            for (int idx = 1; idx < graph.size(); idx++) {
                for (Edge edge : graph.get(idx)) {
                    int u = edge.u; // 与idx对照
                    int v = edge.v;
                    int weight = edge.val;
                    if (minDist[u] != maxVal && minDist[u] + weight < minDist[v]) { // bellman_ford 核心公式
                        minDist[v] = minDist[u] + weight;
                    }
                }
            }
        }

        // 整理结果
        for (int i = 1; i <= n; i++) {
            System.out.print(minDist[i] + "->");
        }
        System.out.println("end");
    }


    public static void main(String[] args) {
        // 输入控制
        Scanner sc = new Scanner(System.in);
        System.out.println("1.输入N个节点、M条边（u v weight）");
        int n = sc.nextInt();
        int m = sc.nextInt();

        System.out.println("2.输入M条边");
        List<List<Edge>> graph = new ArrayList<>(); // 构建邻接表（n个节点：关联节点的边）
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        while (m-- > 0) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();
            graph.get(u).add(new Edge(u, v, weight));
        }

        // 调用bellman算法
        BellmanFordTemplate1.bellmanFord(n, graph, 1);
    }
}
