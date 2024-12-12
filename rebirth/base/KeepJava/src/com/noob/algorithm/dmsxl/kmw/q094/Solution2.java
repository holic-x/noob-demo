package com.noob.algorithm.dmsxl.kmw.q094;

import com.noob.algorithm.dmsxl.graph.Edge;

import java.util.*;

/**
 * KMW094-城市间货物运输 I
 * https://kamacoder.com/problempage.php?pid=1152
 */
public class Solution2 {

    public static int MAX_VAL = Integer.MAX_VALUE;

    /**
     * SPFA 算法： 处理带有负权值的有向图的最短路径问题（起点->终点） bellman_ford 队列优化版本
     *
     * @param n        节点个数
     * @param graph    邻接表
     * @param startIdx 起点
     */
    public static int[] spfa(int n, List<List<Edge>> graph, int startIdx) {
        // 定义minDist[i]：存储【源点】到【节点i】的最短距离（节点编号取值有效范围：[1,n]）
        int[] minDist = new int[n + 1];
        Arrays.fill(minDist, MAX_VAL); // 初始化为最大值
        minDist[startIdx] = 0; // 源点到自身的最短距离为0

        // 定义Queue存储每次松弛操作更新后的节点
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startIdx); // queue的更新始终和minDist的更新保持同步，此处初始化加入源点

        // SPFA 算法核心：对上一次松弛操作更新后的节点关联的边（出）进行松弛操作
        while (!queue.isEmpty()) {
            // 取出队列节点
            int cur = queue.poll();
            // 对该节点关联的边进行松弛操作
            List<Edge> relateEdges = graph.get(cur);
            for (Edge edge : relateEdges) {
                int from = edge.u; // 与cur对照
                int to = edge.v;
                int weight = edge.val;
                if (minDist[from] + weight < minDist[to]) {
                    minDist[to] = minDist[from] + weight; // 更新源点到节点的最短距离
                    if (!queue.contains(to)) { // 如果队列中存在v节点则不重复加入（队列优化）
                        queue.offer(to); // 将v节点加入队列
                    }
                }
            }
        }

        // 整理结果
        return minDist;
    }


    public static void main(String[] args) {
        // 输入控制
        Scanner sc = new Scanner(System.in);
        System.out.println("1.输入n个城市编号，m条道路");
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<List<Edge>> graph = new ArrayList<>(); // 构建邻接表
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        System.out.println("2.输入m行（s t v）表示s城市到t城市的道路权值为v");
        while (m-- > 0) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            int v = sc.nextInt();
            graph.get(s).add(new Edge(s, t, v));
        }

        // 调用bell_ford算法：获取【源点】到其他各个节点的最短路径集合
        int[] minDist = Solution2.spfa(n, graph, 1);
        // 判断起点1到城市n的运输成本
        if (minDist[n] == MAX_VAL) {
            System.out.println("unconnected");
        } else {
            System.out.println(minDist[n]);
        }
    }
}
