package com.noob.algorithm.daily.plan03.hot100_daily.day12;


import java.util.Arrays;
import java.util.Comparator;

public class KruskalTemplate {

    public int kruskal(int[][] edges, int n) {
        int pathSum = 0;

        // ① 对边集合按照权值大小进行排序
        Arrays.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2]; // [u,v,w]
            }
        });

        // ② 遍历所有边，基于并查集处理
        DisjointSetTemplate djs = new DisjointSetTemplate();
        djs.init(n);
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            // 判断u、v是否在同一集合
            boolean isSame = djs.isSame(u, v);
            if (!isSame) {
                djs.join(u, v);
                pathSum += w;
            }
        }

        // 返回结果
        return pathSum;

    }

    public static void main(String[] args) {
        KruskalTemplate kt = new KruskalTemplate();
        int[][] edges = new int[][]{{1, 2, 5}, {1, 3, 6}, {2, 3, 1}};
        System.out.println(kt.kruskal(edges, 4)); // 此处编号节点是从1开始，因此n取大一点用于测试
    }

}
