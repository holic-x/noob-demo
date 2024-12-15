package com.noob.algorithm.dmsxl.kmw.q096;

import com.noob.algorithm.dmsxl.graph.Edge;
import com.noob.algorithm.dmsxl.util.PrintUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * KMW 096 城市间的货物运输III
 * bellmanFord算法 单源有限最短路径
 */
public class Soluion1 {

    /**
     * bellmanFord算法(处理带负权值的有向图的最短路径：起点到终点)
     *
     * @param n        节点个数[1,n]
     * @param edges    边列表集合
     * @param startIdx 开始节点（源点）
     */
    public static int[] bellmanFord(int n, List<Edge> edges, int startIdx, int k) {
        // 定义最大范围
        int maxVal = Integer.MAX_VALUE;
        // minDist[i] 源点到节点i的最短距离
        int[] minDist = new int[n + 1]; // 有效节点编号范围：[1,n]
        Arrays.fill(minDist, maxVal); // 初始化为maxVal
        minDist[startIdx] = 0; // 设置源点到源点的最短路径为0

        int[] minDist_copy = new int[n + 1]; // 每次松弛前记录上一次松弛的结果，作为本次松弛的参考
        // bellmanFord 算法核心:
        for (int i = 1; i <= k + 1; i++) { // 限定松弛k+1次
            // 记录上次松弛的结果
            minDist_copy = Arrays.copyOfRange(minDist, 0, minDist.length);

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
        int[] minDist = Soluion1.bellmanFord(n, edges, src, k);
        // 校验起点1->终点n
        if (minDist[dst] == Integer.MAX_VALUE) {
            System.out.println("unreachable"); // 指定起点到终点不可达
        } else {
            System.out.println("最短路径：" + minDist[n]);
        }
    }
}
