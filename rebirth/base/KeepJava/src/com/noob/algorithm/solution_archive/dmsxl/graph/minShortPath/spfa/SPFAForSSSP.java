package com.noob.algorithm.solution_archive.dmsxl.graph.minShortPath.spfa;

import com.noob.algorithm.solution_archive.dmsxl.graph.Edge;

import java.util.*;

/**
 * SPFA算法（版本3）：处理含【负权回路】的有向图的最短路径问题
 * bellman_ford（版本3） 的队列优化算法版本
 * 限定起点、终点、至多途径k个节点
 */
public class SPFAForSSSP {

    /**
     * SPFA算法
     *
     * @param n        节点个数[1,n]
     * @param graph    邻接表
     * @param startIdx 开始节点（源点）
     */
    public static int[] spfa(int n, List<List<Edge>> graph, int startIdx, int k) {
        // 定义最大范围
        int maxVal = Integer.MAX_VALUE;
        // minDist[i] 源点到节点i的最短距离
        int[] minDist = new int[n + 1]; // 有效节点编号范围：[1,n]
        Arrays.fill(minDist, maxVal); // 初始化为maxVal
        minDist[startIdx] = 0; // 设置源点到源点的最短路径为0

        // 定义queue记录每一次松弛更新的节点
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startIdx); // 初始化：源点开始（queue和minDist的更新是同步的）


        // SPFA算法核心：只对上一次松弛的时候更新过的节点关联的边进行松弛操作
        while (k + 1 > 0 && !queue.isEmpty()) { // 限定松弛 k+1 次
            int curSize = queue.size(); // 记录当前队列节点个数（上一次松弛更新的节点个数，用作分层统计）
            while (curSize-- > 0) { //分层控制，限定本次松弛只针对上一次松弛更新的节点，不对新增的节点做处理
                // 记录当前minDist状态，作为本次松弛的基础
                int[] minDist_copy = Arrays.copyOfRange(minDist, 0, minDist.length);

                // 取出节点
                int cur = queue.poll();
                // 获取cur节点关联的边，进行松弛操作
                List<Edge> relateEdges = graph.get(cur);
                for (Edge edge : relateEdges) {
                    int u = edge.u; // 与`cur`对照
                    int v = edge.v;
                    int weight = edge.val;
                    if (minDist_copy[u] + weight < minDist[v]) {
                        minDist[v] = minDist_copy[u] + weight; // 更新
                        // 队列同步更新（此处有一个针对队列的优化:就是如果已经存在于队列的元素不需要重复添加）
                        if (!queue.contains(v)) {
                            queue.offer(v); // 与minDist[i]同步更新，将本次更新的节点加入队列，用做下一个松弛的参考基础
                        }
                    }
                }
            }
            // 当次松弛结束，次数-1
            k--;
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

        System.out.println("3.输入src dst k（起点、终点、至多途径k个点）");
        int src = sc.nextInt();
        int dst = sc.nextInt();
        int k = sc.nextInt();

        // 调用算法
        int[] minDist = SPFAForSSSP.spfa(n, graph, src, k);
        // 校验起点->终点
        if (minDist[dst] == Integer.MAX_VALUE) {
            System.out.println("unreachable");
        } else {
            System.out.println("最短路径：" + minDist[n]);
        }
    }
}
