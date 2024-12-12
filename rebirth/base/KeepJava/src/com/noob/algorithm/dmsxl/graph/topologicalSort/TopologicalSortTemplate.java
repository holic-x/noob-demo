package com.noob.algorithm.dmsxl.graph.topologicalSort;


import com.noob.algorithm.dmsxl.graph.Edge;

import java.util.*;

/**
 * 拓扑排序代码模板
 */
public class TopologicalSortTemplate {

    // N 个文件([0,N-1])、M 条边（有向依赖关系）
    public void topologicalSort(int n, List<Edge> edges) {
        // 1.记录每个节点的入度、以及当前节点指向什么节点

        /**
         * 定义节点和其关联节点列表（指向的节点列表）：
         * 也可用Map<Integer,List<Integer>>集合形式构建映射关系
         * 此处用List<List<Integer>>(下标表示节点，元素值表示节点指向的节点列表)
         */
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) { // 初始化list
            list.add(new ArrayList<>());
        }

        // 构建每个节点及其入度的依赖关系（基于数组构建）
        int[] inDegree = new int[n];
        for (Edge edge : edges) {
            int u = edge.u, v = edge.v; // u->v 关系校验
            list.get(u).add(v); // 表示获取到u节点关联指向节点列表，然后追加相应的v节点（即处理u->v）
            inDegree[v]++; // u->v,入度是针对v节点校验
        }

        // 2.定义队列存储入度为0的节点
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i); // 初始化将入度为0的节点加入队列
            }
        }

        // 3.遍历处理(拓扑排序核心流程)：选出入度为0的节点进行处理（将其加入结果集、移出图）
        List<Integer> res = new ArrayList<>(); // int[] res = new int[n]; int idx = 0; // 结果集遍历指针
        while (!queue.isEmpty()) {
            // 按顺序取出[入度为0]的节点进行处理
            int cur = queue.poll();
            // 将该节点加入结果集
            res.add(cur); // res[idx++] = cur;
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
        System.out.println("1.输入N个节点、M条边");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        System.out.println("2.输入M条边（a,b）形式");
        // 接收边关系
        List<Edge> edges = new ArrayList<>();
        while (m-- > 0) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            edges.add(new Edge(u, v));
        }

        // 2.调用拓扑排序
        TopologicalSortTemplate solution = new TopologicalSortTemplate();
        solution.topologicalSort(n, edges);
    }

}
