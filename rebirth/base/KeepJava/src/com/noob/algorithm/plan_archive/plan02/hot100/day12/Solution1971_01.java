package com.noob.algorithm.plan_archive.plan02.hot100.day12;

/**
 * 🟢 1971 - 寻找图中是否存在路径
 * 并查集：寻根、构建边、判断两个点是否在同一集合
 */
public class Solution1971_01 {

    class DisJointSet {
        int[] father;

        // 1.初始化
        public void init(int n) {
            father = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
        }

        // 2.寻根
        public int find(int u) {
            if (u == father[u]) {
                return u; // 如果根为自己，直接返回
            }
            // 如果根不是自己，则一层层往下找
            return find(father[u]);

        /*
        father[u] = find(father[u]); // 路径压缩
        return father[u];
         */
        }

        // 3.构建边（v->u）
        public void join(int u, int v) {
            // 分别寻找到u、v的根
            int uRoot = find(u);
            int vRoot = find(v);
            // 如果uRoot、vRoot指向同一个根，说明两个节点本身在同一个集合中直接返回
            if (uRoot == vRoot) {
                return;
            }
            // 让uRoot成为vRoot的根
            father[vRoot] = uRoot;
        }

        // 4.判断两个节点是否在同一个集合
        public boolean isSame(int u, int v) {
            // 分别寻找u、v的根，判断是否指向同一个根
            int uRoot = find(u);
            int vRoot = find(v);
            return uRoot == vRoot;
        }

    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // 构建并查集
        DisJointSet djs = new DisJointSet();
        djs.init(n);
        // 加入边
        for (int[] edge : edges) {
            djs.join(edge[0], edge[1]);
        }
        // 判断两个点是否在同一个集合
        return djs.isSame(source, destination);
    }
}
