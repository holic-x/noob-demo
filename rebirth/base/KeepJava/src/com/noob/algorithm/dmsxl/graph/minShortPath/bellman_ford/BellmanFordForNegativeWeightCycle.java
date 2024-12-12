package com.noob.algorithm.dmsxl.graph.minShortPath.bellman_ford;

import com.noob.algorithm.dmsxl.graph.Edge;
import com.noob.algorithm.dmsxl.util.PrintUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * bellmanFord算法（版本2：处理含【负权回路】的有向图的最短路径问题）（判断是否存在负权回路）
 * bellmanFord算法(处理带负权值的有向图的最短路径：起点到终点)
 * - 针对带有【负权回路】的处理
 */
public class BellmanFordForNegativeWeightCycle {

    /**
     * bellmanFord算法
     *
     * @param n        节点个数[1,n]
     * @param edges    边列表集合
     * @param startIdx 开始节点（源点）
     */
    public static int[] bellmanFord(int n, List<Edge> edges, int startIdx) {
        // 定义最大范围
        int maxVal = Integer.MAX_VALUE;
        // minDist[i] 源点到节点i的最短距离
        int[] minDist = new int[n + 1]; // 有效节点编号范围：[1,n]
        Arrays.fill(minDist, maxVal); // 初始化为maxVal
        minDist[startIdx] = 0; // 设置源点到源点的最短路径为0

        // bellmanFord 算法核心:对所有边松弛n次（n-1次完全松弛，第n次是为了校验是否存在负权回路）
        boolean hasNegativeWeightCycle = false;
        for (int i = 1; i <= n; i++) {
            // 执行bellmanFord核心，遍历所有边
            for (Edge edge : edges) {
                int u = edge.u; // 与idx对照
                int v = edge.v;
                int weight = edge.val;
                if (i < n) {
                    // 前n-1次是bellman_ford算法核心，对所有边执行n-1次松弛
                    if (minDist[u] != maxVal && minDist[u] + weight < minDist[v]) { // bellman_ford 核心公式
                        minDist[v] = minDist[u] + weight;
                    }
                } else if (i == n) {
                    // 第n次松弛则是为了校验是否存在【负权回路】
                    if (minDist[u] != maxVal && minDist[u] + weight < minDist[v]) {
                        // 出现了更短的路径，说明存在【负权回路】
                        hasNegativeWeightCycle = true;
                    }
                }
            }
            System.out.println("第" + i + "次松弛");
            PrintUtil.print(minDist); // 打印数组
        }

        if (hasNegativeWeightCycle) {
            System.out.println("[负权回路]出现标识：" + hasNegativeWeightCycle);
        } else {
            // 校验起点1->终点n
            if (minDist[n] == Integer.MAX_VALUE) {
                System.out.println("[起点1]到[终点n]不可达");
            } else {
                System.out.println("[起点1]到[终点n]的最短路径：" + minDist[n]);
            }
        }

        // 返回minDist
        return minDist;
    }

    public static void main(String[] args) {
        // 输入控制
        Scanner sc = new Scanner(System.in);
        System.out.println("1.输入N个节点、M条边（u v weight）");
        int n = sc.nextInt();
        int m = sc.nextInt();

        System.out.println("2.输入M条边");
        List<Edge> edges = new ArrayList<>(); // 构建边集合
        while (m-- > 0) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();
            edges.add(new Edge(u, v, weight));
        }

        // 调用bellman算法
        BellmanFordForNegativeWeightCycle.bellmanFord(n, edges, 1);
    }
}
