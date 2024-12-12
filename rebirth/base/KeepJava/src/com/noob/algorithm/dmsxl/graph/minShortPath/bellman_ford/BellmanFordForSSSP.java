package com.noob.algorithm.dmsxl.graph.minShortPath.bellman_ford;

import com.noob.algorithm.dmsxl.graph.Edge;
import com.noob.algorithm.dmsxl.util.PrintUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * bellmanFord算法（版本3：处理限定至多途径k个节点的单源最短路径问题）
 * 限定起点、终点、至多途径k个节点
 */
public class BellmanFordForSSSP {

    /**
     * bellmanFord算法
     *
     * @param n        节点个数[1,n]
     * @param edges    边列表集合
     * @param startIdx 开始节点（源点）
     * @param k        至多途径节点个数
     */
    public static int[] bellmanFord(int n, List<Edge> edges, int startIdx, int k) {
        // 定义最大范围
        int maxVal = Integer.MAX_VALUE;
        // minDist[i] 源点到节点i的最短距离
        int[] minDist = new int[n + 1]; // 有效节点编号范围：[1,n]
        Arrays.fill(minDist, maxVal); // 初始化为maxVal
        minDist[startIdx] = 0; // 设置源点到源点的最短路径为0

        // bellmanFord 算法核心: 限定对所有边松弛`k+1`次
        for (int i = 1; i <= k + 1; i++) { // 处理① 限定松弛k+1次
            // 处理② 限定每次松弛都是基于上次松弛是更新的状态
            int[] minDist_copy = Arrays.copyOfRange(minDist, 0, minDist.length);

            // 执行bellmanFord核心，遍历所有边
            for (Edge edge : edges) {
                int u = edge.u; // 与idx对照
                int v = edge.v;
                int weight = edge.val;
                if (minDist_copy[u] != maxVal && minDist_copy[u] + weight < minDist[v]) { // bellman_ford 核心公式
                    minDist[v] = minDist_copy[u] + weight;
                }
            }
            System.out.println("第" + i + "次松弛");
            PrintUtil.print(minDist); // 打印数组
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

        System.out.println("3.输入起点src、终点dst、途径城市数量限制 k");
        int src = sc.nextInt();
        int dst = sc.nextInt();
        int k = sc.nextInt();

        // 调用bellman算法
        int[] minDist = BellmanFordForSSSP.bellmanFord(n, edges, src, k);
        // 校验起点->终点
        if (minDist[dst] == Integer.MAX_VALUE) {
            System.out.println("unreachable");
        } else {
            System.out.println("最短路径：" + minDist[n]);
        }
    }
}
