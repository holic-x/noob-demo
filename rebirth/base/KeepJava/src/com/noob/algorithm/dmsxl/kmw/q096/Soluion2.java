package com.noob.algorithm.dmsxl.kmw.q096;

import com.noob.algorithm.dmsxl.graph.Edge;
import com.noob.algorithm.dmsxl.util.PrintUtil;

import java.util.*;

/**
 * SPFA算法 单源有限最短路径
 */
public class Soluion2 {

    /**
     * bellmanFord算法(处理带负权值的有向图的最短路径：起点到终点)
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

        int[] minDist_copy = new int[n + 1];  // 每次松弛前记录上一次松弛的结果，作为本次松弛的参考

        // spfa 算法核心
        Queue<Integer> queue = new LinkedList<>(); // 记录每一次松弛更新的节点
        queue.offer(startIdx); // 初始化

        while ((k + 1) > 0 && !queue.isEmpty()) {
            // 每次松弛前记录当前数组状态，作为本次松弛操作的参考
            minDist_copy = Arrays.copyOfRange(minDist, 0, minDist.length);
            // 记录当次松弛要处理的节点个数
            int curSize = queue.size();
            while (curSize-- > 0) {
                // 取出节点
                int cur = queue.poll();
                // 对节点连接的边进行松弛操作
                List<Edge> relateEdges = graph.get(cur);
                for (Edge edge : relateEdges) {
                    int from = edge.u; // 与cur节点对照
                    int to = edge.v;
                    int weight = edge.val;
                    // 松弛校验处理
                    if (minDist_copy[from] + weight < minDist[to]) {
                        minDist[to] = minDist_copy[from] + weight; // 更新节点
                        if (!queue.contains(to)) {
                            queue.offer(to); // 如果队列中已存在节点则不重复加入
                        }
                    }
                }
            }

            // 当次松弛操作结束，计数减1
            k--;
//            System.out.println("第" + i + "次松弛");
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

        System.out.println("3.输入起点src、终点dst、途径城市数量限制 k");
        int src = sc.nextInt();
        int dst = sc.nextInt();
        int k = sc.nextInt();

        // 调用bellman算法
        int[] minDist = Soluion2.spfa(n, graph, src, k);
        // 校验起点1->终点n
        if (minDist[dst] == Integer.MAX_VALUE) {
            System.out.println("unreachable"); // 指定起点到终点不可达
        } else {
            System.out.println("最短路径：" + minDist[n]);
        }
    }
}
