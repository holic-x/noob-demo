package com.noob.algorithm.solution_archive.dmsxl.kmw.q053;

import com.noob.algorithm.solution_archive.dmsxl.graph.disJointSet.DisJointSet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * KMW053-寻宝（最小生成树基础题型）
 */
public class Solution3 {
    /**
     * Kruskal 算法
     *
     * @param n     顶点个数
     * @param edges 边及对应权值关系
     */
    public static int kruskal(int n, List<Edge> edges) {
        DisJointSet disjointSet = new DisJointSet();
        disjointSet.init(n + 1); // 并查集初始化

        // 1.边排序：对map的value进行排序，此处转化为List处理
        edges.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.val - o2.val; // 根据边的权值排序
            }
        });

        // 2.边遍历：遍历排序后的边
        int sum = 0;
        for (Edge edge : edges) {
            int u = edge.u, v = edge.v;
            if (!disjointSet.isSame(u, v)) {
                // 如果u、v不在一个集合，加入该边（加入并查集）
                disjointSet.join(u, v);
                System.out.println(u + "->" + v); // 输入加入的边
                sum += edge.val;
            }
        }

        // 返回结果
        System.out.println("最小路径总和：" + sum);
        return sum;
    }


    public static void main(String[] args) {
        // 输入控制
        Scanner sc = new Scanner(System.in);
        System.out.println("1.输入V顶点数量，E边数");
        int v = sc.nextInt();
        int e = sc.nextInt();
        System.out.println("2.输入边关系（a b c）表示a连接b，权值为c");
        // 通过Map<edge,val>存储边的权值关系(key为`a->b`,value为`c`)
        List<Edge> edges = new ArrayList<>();
        while (e-- > 0) {
            int node1 = sc.nextInt();
            int node2 = sc.nextInt();
            int val = sc.nextInt();
            // 构建无向图的边关系
            edges.add(new Edge(node1, node2, val));
            // edges.put(node2 + "->" + node1, val); 此处只需要判断边，因此只需要校验一条边的两个端点即可，不需要存储两次
        }

        // 调用kruskal算法获取最小连通图的路径总和
        Solution3.kruskal(v, edges);
    }
}

class Edge {
    int u; // 边的端点1
    int v; // 边的端点2
    int val; // 边的权值

    public Edge() {
    }

    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
        this.val = 0;
    }

    public Edge(int u, int v, int val) {
        this.u = u;
        this.v = v;
        this.val = val;
    }
}