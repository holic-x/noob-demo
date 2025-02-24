package com.noob.algorithm.solution_archive.dmsxl.kmw.q109;

import java.util.*;

/**
 * KMW109 冗余连接II
 */
public class Solution1 {

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

    // 5.在有向图里找到删除的那条边，使其变成树
    public static void getRemoveEdge(int n, List<int[]> edges) {
        init(n); // 初始化并查集
        for (int i = 0; i < edges.size(); i++) { // 遍历所有的边
            if (isSame(edges.get(i)[0], edges.get(i)[1])) { // 如果构成有向环了，就是要删除的边
                System.out.println(edges.get(i)[0] + " " + edges.get(i)[1]);
                return;
            } else {
                join(edges.get(i)[0], edges.get(i)[1]);
            }
        }
    }

    // 6.删一条边之后判断是不是树
    public static boolean isTreeAfterRemoveEdge(int n, List<int[]> edges, int deleteEdge) {
        init(n); // 初始化并查集
        for (int i = 0; i < edges.size(); i++) {
            if (i == deleteEdge) continue; // 如果遇到要删除的边，则跳过
            if (isSame(edges.get(i)[0], edges.get(i)[1])) { // 如果构成有向环了，一定不是树
                return false;
            }
            join(edges.get(i)[0], edges.get(i)[1]);
        }
        return true;
    }


    public static void main(String[] args) {

        Solution1 solution = new Solution1();

        // 输入控制
        Scanner sc = new Scanner(System.in);
        System.out.println("1.输入节点个数N、边数M：");
        int n = sc.nextInt();
        int m = sc.nextInt();
        System.out.println("2.输入边");
        sc.nextLine();
        List<int[]> edges = new ArrayList<>();
        // Map<Integer, Integer> nodeEntryDegreeMap = new HashMap<>(); // 也可以选用数组处理
        int[] nodeEntryDegree = new int[n + 1];
        while (m-- > 0) {
            // String[] inputStr = sc.nextLine().split("\\s+");
            int u = sc.nextInt();
            int v = sc.nextInt();
            edges.add(new int[]{u, v}); // 记录边
            // nodeEntryDegreeMap.put(u, nodeEntryDegreeMap.getOrDefault(u, 0) + 1); // 记录每个节点的入度
            nodeEntryDegree[u]++; // 记录每个节点的入度
        }

        /**
         * 分情况处理：判断是否存在入度为2的节点：
         * 1.如果存在入度为2的节点，则尝试删除每一条边，判断删除后是否可以构成有向图
         * 2.如果不存在入度为2的节点，则说明存在有向环，在有向图中删除构成有向环的那条边（这里的处理思路和【108冗余连接】一样）
         */

        // 判断是否存在入度为2
        List<Integer> vec = new ArrayList<>(); // 记录入度为2的边（如果有的话就两条边）
        // 找入度为2的节点所对应的边，注意要倒序，因为优先删除最后出现的一条边
        for (int i = n - 1; i >= 0; i--) {
            if (nodeEntryDegree[edges.get(i)[1]] == 2) {
                vec.add(i);
            }
        }

        if (vec.size() > 0) {
            // 处理情况① ② ：vec里的边已经按照倒序放的，所以优先删 vec.get(0) 这条边
            if (isTreeAfterRemoveEdge(n + 1, edges, vec.get(0))) {
                System.out.println(edges.get(vec.get(0))[0] + " " + edges.get(vec.get(0))[1]);
            } else {
                System.out.println(edges.get(vec.get(1))[0] + " " + edges.get(vec.get(1))[1]);
            }
            return;
        } else {
            // 处理情况③：有向环的情况
            solution.getRemoveEdge(n + 1, edges);
        }

    }
}
