package com.noob.algorithm.dmsxl.graph.minShortPath.spfa;

import com.noob.algorithm.dmsxl.graph.Edge;

import java.util.*;

/**
 * SPFA算法(处理带负权值的有向图的最短路径：起点到终点) bellman_ford 的队列优化算法版本
 * - 针对带有【负权回路】的处理
 */

public class SPFAForNegativeWeightCycle {

    /**
     * SPFA算法(处理带负权值的有向图的最短路径：起点到终点) bellman_ford 的队列优化算法版本
     *
     * @param n        节点个数[1,n]
     * @param graph    邻接表
     * @param startIdx 开始节点（源点）
     */
    public static int[] bellmanFord(int n, List<List<Edge>> graph, int startIdx) {
        // 定义最大范围
        int maxVal = Integer.MAX_VALUE;
        // minDist[i] 源点到节点i的最短距离
        int[] minDist = new int[n + 1]; // 有效节点编号范围：[1,n]
        Arrays.fill(minDist, maxVal); // 初始化为maxVal
        minDist[startIdx] = 0; // 设置源点到源点的最短路径为0

        // 定义queue记录每一次松弛更新的节点
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startIdx); // 初始化：源点开始（queue和minDist的更新是同步的）

        // 定义数组统计每个节点加入队列的次数
        int[] cnt = new int[n + 1]; // 下标对应节点，元素值对应节点被加入队列的次数统计
        cnt[startIdx] = 1;
        boolean hasNegativeWeightCycle = false;
        // SPFA算法核心：只对上一次松弛的时候更新过的节点关联的边进行松弛操作
        while (!queue.isEmpty()) {
            // 取出节点
            int cur = queue.poll();
            // 获取cur节点关联的边，进行松弛操作
            List<Edge> relateEdges = graph.get(cur);
            for (Edge edge : relateEdges) {
                int u = edge.u; // 与`cur`对照
                int v = edge.v;
                int weight = edge.val;
                if (minDist[u] + weight < minDist[v]) {
                    minDist[v] = minDist[u] + weight; // 更新
                    // 队列同步更新（此处有一个针对队列的优化:就是如果已经存在于队列的元素不需要重复添加）
                    if (!queue.contains(v)) {
                        queue.offer(v); // 与minDist[i]同步更新，将本次更新的节点加入队列，用做下一个松弛的参考基础
                        cnt[v]++; // 节点加入队列次数更新
                    }

                    // 同步判断cnt[v]是否超出n-1，以控制循环正常跳出
                    if (cnt[v] > n - 1) {
                        hasNegativeWeightCycle = true;
                        // 此处主动将queue清空，用于跳出外层queue的遍历
                        queue.clear();
                        break;
                    }
                }
            }
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
        List<List<Edge>> graph = new ArrayList<>(); // 构建邻接表
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
        SPFAForNegativeWeightCycle.bellmanFord(n, graph, 1);
    }
}
