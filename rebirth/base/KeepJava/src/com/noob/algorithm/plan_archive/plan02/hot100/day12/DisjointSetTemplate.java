package com.noob.algorithm.plan_archive.plan02.hot100.day12;

/**
 * 并查集：寻根、构建边、判断两个点是否在同1集合
 */
public class DisjointSetTemplate {

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

    public static void main(String[] args) {
        // 模拟操作
        DisjointSetTemplate djs = new DisjointSetTemplate();
        djs.init(10);
        djs.join(1, 8);// 加入 (1,8)
        djs.join(3, 8);// 加入 (3,8)
        djs.join(1, 7);// 加入 (1,7)
        djs.join(8, 5);// 加入 (8,5)
        djs.join(2, 9);// 加入 (2,9)
        djs.join(6, 9);// 加入 (6,9)
        // 判断8、7节点是否在同一个集合
        System.out.println(djs.isSame(8, 7));
        // 判断7、2节点是否在同一个集合
        System.out.println(djs.isSame(7, 2));


    }

}
