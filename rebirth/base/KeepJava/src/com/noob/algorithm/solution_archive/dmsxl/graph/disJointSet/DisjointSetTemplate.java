package com.noob.algorithm.solution_archive.dmsxl.graph.disJointSet;

import com.noob.algorithm.solution_archive.dmsxl.util.PrintUtil;

public class DisjointSetTemplate {

    static int n = 10;
    static int[] father = new int[n];

    // 1.并查集初始化
    static void init() {
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }

    // 2.并查集里寻根的过程
    static int find(int u) {
        if (u == father[u]) {
            return u; // 如果根就是自己，直接返回
        }
        // else return father[u] = find(father[u]); // 路径压缩
        else {
            father[u] = find(father[u]); // 路径压缩
            return father[u];
        }
    }

    // 3.判断u、v是否同一个集合中（即判断u、v是否指向同一个根）
    static boolean isSame(int u, int v) {
        int uRoot = find(u);
        int vRoot = find(v);
        return uRoot == vRoot;
    }

    // 4.将v->u这条边加入并查集
    static void join(int u, int v) {
        u = find(u); // 寻找u的根
        v = find(v); // 寻找v的根
        if (u == v) return; // 如果发现根相同，则说明在一个集合，不用两个节点相连直接返回
        father[v] = u;
    }

    // 4.将v->u这条边加入并查集（版本测试）
    static void joinErr(int u, int v) {
        if (isSame(u, v)) {
            return;
        }
        father[v] = u;
    }

    public static void main(String[] args) {
        DisjointSetTemplate dst = new DisjointSetTemplate();

        System.out.println("代码1版本测试：");
        dst.init();
        dst.joinErr(1, 2);
        dst.joinErr(3, 2);
        PrintUtil.print(dst.father);
        System.out.println("节点1的根节点：" + dst.find(1));
        System.out.println("节点3的根节点：" + dst.find(3));
        System.out.println(dst.isSame(1, 3)); // false(不符合预期)

        System.out.println("------------------------------------------------------------");
        System.out.println("代码2版本测试：");
        dst.init();
        dst.join(1, 2);
        dst.join(3, 2);
        PrintUtil.print(dst.father);
        System.out.println("节点1的根节点：" + dst.find(1));
        System.out.println("节点3的根节点：" + dst.find(3));
        System.out.println(dst.isSame(1, 3)); // true
    }
}
