package com.noob.algorithm.solution_archive.dmsxl.graph.minShortPath.dijkstra;

import com.noob.algorithm.solution_archive.dmsxl.graph.Edge;
import com.noob.algorithm.solution_archive.dmsxl.graph.Pair;

import java.util.*;

/**
 * 自定义类维护节点及关联属性
 */
class Node {
    public int nodeIdx; // 节点值
    public int sourceToMinDist; // 源点到当前节点的最短距离

    Node() {
    }

    Node(int nodeIdx, int sourceToMinDist) {
        this.nodeIdx = nodeIdx;
        this.sourceToMinDist = sourceToMinDist;
    }
}


/**
 * dijkstra 迪杰斯特拉算法（邻接表处理方式,堆优化版本）
 */
public class DijkstraTemplate5 {

    /**
     * @param n        节点个数
     * @param graph    邻接表（图）
     * @param startIdx 源点
     */
    public static void dijkstra(int n, List<List<Edge>> graph, int startIdx) {
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

        // 定义小顶堆，维护`minDist[]`的有序性
        PriorityQueue<Node> pq = new PriorityQueue<>(
                new Comparator<Node>() {
                    @Override
                    public int compare(Node o1, Node o2) {
                        return o1.sourceToMinDist - o2.sourceToMinDist; // 根据【源点到当前节点的最短距离】从小到大进行排序
                    }
                }
        );
        pq.offer(new Node(startIdx, 0)); // 初始化队列：开始节点为源点（startIdx，源点距源点最短距离为0）

        // 主循环（dijkstra算法核心：dijkstra三部曲）
        while (!pq.isEmpty()) {
            // 1.选择距离源点的最近节点(从小顶堆中获取)
            Node cur = pq.poll();

            // 2.将这个最近节点标记为已被选择（遍历）
            selected[cur.nodeIdx] = true;

            // 3.更新经由当前选择节点扩展的新路径（cur可到达的其他节点）
            List<Edge> relateEdges = graph.get(cur.nodeIdx);
            for (Edge edge : relateEdges) {
                if (!selected[edge.v]) {
                    minDist[edge.v] = Math.min(minDist[edge.v], minDist[cur.nodeIdx] + edge.val);
                    pq.offer(new Node(edge.v, edge.v)); // 更新midDist的同时同步更新优先队列
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
        // 定义邻接表（表示每个节点关联的边(可以从边定义中跟踪到(u,v,w)关系)）
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            // 初始化邻接表：下标索引对应为节点u(取值范围：[1,n]),下标对应元素值为节点u关联的边关系
            graph.add(new ArrayList<>());
        }
        // 处理边关系
        while (m-- > 0) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();
            graph.get(u).add(new Edge(u, v, weight));
        }

        // 调用算法
        DijkstraTemplate5 dijkstraTemplate = new DijkstraTemplate5();
        dijkstraTemplate.dijkstra(n, graph, 1);
    }
}


