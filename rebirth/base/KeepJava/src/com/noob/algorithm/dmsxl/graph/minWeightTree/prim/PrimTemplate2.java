package com.noob.algorithm.dmsxl.graph.minWeightTree.prim;

import java.util.*;

/**
 * 最小生成树算法模板2（基于邻接矩阵）
 */
public class PrimTemplate2 {

    /**
     * prim 算法：最小生成树
     */
    public static int prim(int n, int[][] graph) {
        int maxVal = 10001; // Integer.MAX_VALUE
        // 定义minDist[] 存储非生成树节点距离生成树的最小距离
        int[] minDist = new int[n + 1]; // 节点有效范围取值为[1,n]
        Arrays.fill(minDist, maxVal); // 初始化：初始化最近距离为最大（或者结合题目设定max：10001）

        String[] minDistEdge = new String[n + 1]; // 当前最小距离对应的边关系(例如`(a,b)`)

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
                if (!tree.contains(i) && graph[cur][i] != 0 && graph[cur][i] < minDist[i]) { // 需注意此处需限定graph[cur][i]!=0表示只校验两个节点有直接路径的情况（无直接路径表示这两个节点不可达）
                    // 更新非生成树节点到最小生成树的最近距离 并 记录这个边关系
                    minDist[i] = graph[cur][i];
                    minDistEdge[i] = cur + "->" + i;
                }
            }
        }

        // 当所有的节点都选出，构成一条完整路径，此时minDist存储的为最小生成树的最近距离、minDistEdge存储对应的边关系，累加总和得到最小生成树路径和
        int sum = 0;
        for (int i = 2; i <= n; i++) { // 从第2个节点开始计算路径
            sum += minDist[i];
            System.out.println(minDistEdge[i]);
        }
        return sum;
    }

    public static void main(String[] args) {
        // 输入控制
        /*
        Scanner sc = new Scanner(System.in);
        System.out.println("输入N个节点、M条边及其权值关系");
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 构建邻接矩阵，元素值存储边的权值
        int[][] graph = new int[n + 1][n + 1]; // 节点有效范围取值[1,n]

        while (m-- > 0) {
            int edge1 = sc.nextInt();
            int edge2 = sc.nextInt();
            int val = sc.nextInt();
            // 构建无向图(双边)
            graph[edge1][edge2] = val;
            graph[edge2][edge1] = val;
        }
         */
        int n = 7;
        int[][] graph = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 1, 0, 2, 0, 0}, {0, 1, 0, 2, 2, 0, 1, 0},
                {0, 1, 2, 0, 1, 0, 0, 0}, {0, 0, 2, 1, 0, 1, 0, 0}, {0, 2, 0, 0, 1, 0, 2, 1},
                {0, 0, 1, 0, 0, 2, 0, 1}, {0, 0, 0, 0, 0, 1, 1, 0}
        };

        // 调用prim算法
        int res = PrimTemplate2.prim(n, graph);
        System.out.println("最小生成树路径和：" + res);
    }

}
