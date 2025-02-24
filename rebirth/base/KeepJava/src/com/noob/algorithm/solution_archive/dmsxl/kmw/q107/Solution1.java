package com.noob.algorithm.solution_archive.dmsxl.kmw.q107;


import java.util.Scanner;

/**
 * 并查集模板
 */
class DisJoint {

    static int[] father;

    // 1.init
    static void init(int n) {
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }

    // 2.find 寻根
    static int find(int u) {
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
    static void join(int u, int v) {
        int uRoot = find(u);
        int vRoot = find(v);
        if (uRoot == vRoot) {
            return; // 如果同根则说明本来就在同一个集合中，不操作
        }
        father[vRoot] = uRoot; // v指向u表示：father[v] = u
    }

    // 4. isSame 判断两个节点是否在同一个集合（是否同根）
    static boolean isSame(int u, int v) {
        int uRoot = find(u);
        int vRoot = find(v);
        return uRoot == vRoot;
    }
}

/**
 * KMW107 寻找存在的路径
 */
public class Solution1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("1.输入n（节点个数）、m（边数）");
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 初始化并查集
        DisJoint disJoint = new DisJoint();
        disJoint.init(n + 1); // 编号有效范围为[1,n]，此处加一位

        System.out.println("2.输入m条边（x y）以空格间隔数字");
        sc.nextLine();
        while (m-- > 0) {
            String[] inputStr = sc.nextLine().split("\\s+");
            // 加入边
            disJoint.join(Integer.valueOf(inputStr[0]), Integer.valueOf(inputStr[1]));
        }

        System.out.println("3.输入source destination");
        String[] inputStr = sc.nextLine().split("\\s+");
        System.out.println(disJoint.isSame(Integer.valueOf(inputStr[0]), Integer.valueOf(inputStr[1])));
    }

}
