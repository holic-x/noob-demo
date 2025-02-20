package com.noob.algorithm.solution_archive.dmsxl.kmw.q095;

import com.noob.algorithm.solution_archive.dmsxl.graph.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * KMW095-城市间货物运输 II
 * https://kamacoder.com/problempage.php?pid=1153
 * 针对带有【负权回路】的处理
 */
public class Solution1 {

    public static int MAX_VAL = Integer.MAX_VALUE;

    /**
     * bellman_ford 算法： 处理带有负权值的有向图的最短路径问题（起点->终点）
     *
     * @param n        节点个数
     * @param edges    边列表
     * @param startIdx 起点
     */
    public static int[] bellman_ford(int n, List<Edge> edges, int startIdx) {
        // 定义minDist[i]：存储【源点】到【节点i】的最短距离（节点编号取值有效范围：[1,n]）
        int[] minDist = new int[n + 1];
        Arrays.fill(minDist, MAX_VAL); // 初始化为最大值
        minDist[startIdx] = 0; // 源点到自身的最短距离为0

        // bellman_ford 算法核心：执行n次松弛（n-1次正常松弛，第n次松弛校验是否存在负权回路）
        boolean hasNegCycle = false;
        for (int i = 1; i <= n; i++) { // n 次松弛
            // 对每条边执行松弛操作
            for (Edge edge : edges) {
                int from = edge.u;
                int to = edge.v;
                int weight = edge.val;
                if (i < n) {
                    if (minDist[from] != MAX_VAL && minDist[from] + weight < minDist[to]) { // 设定minDist[from]!= MAX_VAL避免从未被计算过的节点出发（无意义）
                        minDist[to] = minDist[from] + weight; // 出现最短路径，更新
                    }
                } else if (i == n) {
                    if (minDist[from] != MAX_VAL && minDist[from] + weight < minDist[to]) { // 设定minDist[from]!= MAX_VAL避免从未被计算过的节点出发（无意义）
                        // 出现最短路径，说明存在负权回路
                        hasNegCycle = true;
                    }
                }

            }
        }

        if (hasNegCycle) {
            System.out.println("cycle");
        } else {
            // 判断起点1到城市n的运输成本
            if (minDist[n] == MAX_VAL) {
                System.out.println("unconnected");
            } else {
                System.out.println(minDist[n]);
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

        List<Edge> edges = new ArrayList<>(); // 收集边列表
        System.out.println("2.输入m行（s t v）表示s城市到t城市的道路权值为v");
        while (m-- > 0) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            int v = sc.nextInt();
            edges.add(new Edge(s, t, v));
        }

        // 调用bell_ford算法：获取【源点】到其他各个节点的最短路径集合
        Solution1.bellman_ford(n, edges, 1);
    }
}
