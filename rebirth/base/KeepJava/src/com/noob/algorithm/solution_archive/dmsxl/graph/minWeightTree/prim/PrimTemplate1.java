package com.noob.algorithm.solution_archive.dmsxl.graph.minWeightTree.prim;

import java.util.*;

/**
 * 最小生成树算法模板1（核心模板思路）
 */
public class PrimTemplate1 {

    /**
     * prim 算法：最小生成树
     */
    public static int prim(int n, Map<String, Integer> edges) {
        int maxVal = 10001; // Integer.MAX_VALUE
        // 定义minDist[] 存储非生成树节点距离生成树的最小距离
        int[] minDist = new int[n + 1]; // 节点有效范围取值为[1,n]
        Arrays.fill(minDist, maxVal); // 初始化：初始化最近距离为最大（或者结合题目设定max：10001）

        // 构建minDist
        List<Integer> tree = new ArrayList<>(); // 存储最小生成树节点

        /**
         * prim 主循环函数
         * 这个prim过程会执行n次，但[cnt<n || cnt<=n 这两个条件都不影响最终路径和]，因为当更新到倒数第二个节点的时候实际上最后一个节点已经是明确的了（minDist更新完成）
         * 所以cnt<n、cnt<=n这两个条件并不影响minDist中的路径总和累加，唯一不同的是tree这个最小生成树的标记
         * - cnt<n 执行会选择n-1个节点加入到tree中（最后一步也已经是明确的）
         * - cnt<=n 执行会将所有节点加入到tree中
         */
        for (int cnt = 1; cnt <= n; cnt++) {
            // 1.选出要加入最小生成树的节点
            /*
            int cur = 1; // 初始化
            for (int i = 1; i <= n; i++) {
                if (!tree.contains(i) && minDist[i] < minDist[cur]) {
                    cur = i;
                }
            }
             */
            int cur = -1, curMin = maxVal + 1; // 推荐写法：初始化当前最近距离(处理初始minDist中的值都为maxVal的设定，确保可以选择一个节点)
            // int cur = 1, curMin = minDist[cur]; // 初始化当前最近距离(也可以默认为1，在后面的循环中会更新cue的值)
            for (int i = 1; i <= n; i++) {
                if (!tree.contains(i) && minDist[i] < curMin) {
                    cur = i;
                    curMin = minDist[i];
                }
            }

            // 2.将选出的节点加入最小生成树
            tree.add(cur);

            // 3.更新非生成树节点与choose的节点的最近距离
            for (int i = 1; i <= n; i++) {
                // 更新minDist（更新非生成树节点到choose节点的最近距离）
                if (!tree.contains(i)) {
                    // 更新非生成树节点到最小生成树的最近距离
                    int edgeVal = edges.getOrDefault(cur + "," + i, Integer.MAX_VALUE);
                    minDist[i] = Math.min(minDist[i], edgeVal);
                }
            }
        }

        // 当所有的节点都选出，构成一条完整路径，此时minDist存储的为最小生成树的最近距离，累加总和得到最小生成树路径和
        int sum = 0;
        for (int i = 2; i <= n; i++) { // 从第2个节点开始计算路径
            sum += minDist[i];
        }
        return sum;
    }


    public static void main(String[] args) {
        // 输入控制
        Scanner sc = new Scanner(System.in);
        System.out.println("输入N个节点、M条边及其权值关系");
        int n = sc.nextInt();
        int m = sc.nextInt();
        // Map<int[], Integer> edges = new HashMap<>(m); // (a,b) 权值v:Map<(a,b),val>
        Map<String, Integer> edges = new HashMap<>(m); // (a,b) 权值v:Map<(a,b),val>
        while (m-- > 0) {
            int edge1 = sc.nextInt();
            int edge2 = sc.nextInt();
            int val = sc.nextInt();
            // 构建无向图(双边)
            edges.put(edge1 + "," + edge2, val);
            edges.put(edge2 + "," + edge1, val);
        }

        /*
        int n = 7;
        Map<String, Integer> edges = new HashMap<>();
        edges.put("1,2", 1);
        edges.put("1,3", 1);
        edges.put("1,5", 2);
        edges.put("2,6", 1);
        edges.put("2,4", 2);
        edges.put("2,3", 2);
        edges.put("3,4", 1);
        edges.put("4,5", 1);
        edges.put("5,6", 2);
        edges.put("5,7", 1);
        edges.put("6,7", 1);

        edges.put("2,1", 1);
        edges.put("3,1", 1);
        edges.put("5,1", 2);
        edges.put("6,2", 1);
        edges.put("4,2", 2);
        edges.put("3,2", 2);
        edges.put("4,3", 1);
        edges.put("5,4", 1);
        edges.put("6,5", 2);
        edges.put("7,5", 1);
        edges.put("7,6", 1);
         */

        // 调用prim算法
        int res = PrimTemplate1.prim(n, edges);
        System.out.println("最小生成树路径和：" + res);
    }

}
