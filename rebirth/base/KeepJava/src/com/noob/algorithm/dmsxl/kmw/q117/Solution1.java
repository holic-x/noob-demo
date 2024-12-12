package com.noob.algorithm.dmsxl.kmw.q117;


import com.noob.algorithm.dmsxl.graph.Edge;

import java.util.*;

/**
 * KMW 117 软件构建
 */
public class Solution1 {

    // N 个文件([0,N-1])、M 条边（有向依赖关系）
    public void topologicalSort(int n, List<Edge> edges) {
        // 1.记录每个节点的入度、以及当前节点指向什么节点
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        int[] inDegree = new int[n]; // 构建每个节点及其入度的依赖关系（基于数组构建）
        for (Edge edge : edges) {
            int u = edge.u, v = edge.v; // u->v 关系校验
            list.get(u).add(v); // 表示获取到u节点的指向节点列表然后追加相应的v节点（即处理u->v）
            inDegree[v]++; // u->v,入度是针对v节点校验
        }

        // 2.定义队列存储入度为0的节点
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i); // 初始化将入度为0的节点加入队列
            }
        }

        // 3.遍历处理
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            // 按顺序取出[入度为0]的节点进行处理
            int cur = queue.poll();
            // 将该节点加入结果集
            res.add(cur);
            // 将节点从图中移除（此处移除不是真的移除，而是处理边关系）
            List<Integer> link = list.get(cur);
            for (int node : link) {
                inDegree[node]--; // cur 节点指向的节点的入度都减1
                // 更新后发现当前节点入度为0则加入queue
                if (inDegree[node] == 0) {
                    queue.add(node);
                }
            }
        }

        // 4.结果打印
        if (res.size() == n) {
            for (int node : res) {
                System.out.print(node + "->");
            }
            System.out.println("end");
        } else {
            // 结果节点个数和实际节点个数不匹配，说明存在环，无法获取正常的拓扑序列
            System.out.println("-1");
        }
    }


    public static void main(String[] args) {
        // 1.输入处理
        // N 个文件([0,N-1])、M 条边（有向依赖关系）
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        // 接收文件依赖关系（实际对照的就是有向图的边关系）
        List<Edge> links = new ArrayList<>();
        while (m-- > 0) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            links.add(new Edge(u, v));
        }

        // 2.调用拓扑排序
        Solution1 solution = new Solution1();
        solution.topologicalSort(n, links);
    }

}
