package com.noob.algorithm.dmsxl.graph.disJointSet;


/**
 * 并查集模板
 */
public class DisJoint {

    public static int[] father;

    // 1.init
    public static void init(int n) {
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }

    // 2.find 寻根
    public static int find(int u) {
        if (u == father[u]) {
            return u; // 如果为自身则直接返回
        } else {
            // return find(father[u]); // 递归寻根
            /**
             * // 路径压缩：缩短检索时间
             * father[u] = find(father[u]);
             * return father[u];
             */
            father[u] = find(father[u]); // 路径压缩：缩短检索时间
            return father[u];
        }
    }

    // 3. join 构建边（将两个节点加入集合）
    public static void join(int u, int v) {
        int uRoot = find(u);
        int vRoot = find(v);
        if (uRoot == vRoot) {
            return; // 如果同根则说明本来就在同一个集合中，不操作
        }
        father[vRoot] = uRoot; // v指向u表示：father[v] = u
    }

    // 4. isSame 判断两个节点是否在同一个集合（是否同根）
    public static boolean isSame(int u, int v) {
        int uRoot = find(u);
        int vRoot = find(v);
        return uRoot == vRoot;
    }
}

