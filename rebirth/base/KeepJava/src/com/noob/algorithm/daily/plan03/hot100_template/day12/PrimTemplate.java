package com.noob.algorithm.daily.plan03.hot100_template.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 最小生成树：Prim 算法
 */
public class PrimTemplate {

    /**
     * prim 算法核心：
     * ① 维护一个数组minDist存储每个点到最小生成树的最短距离
     * ② 选择过程
     * - 2.1 每次选中一个距离最小生成树最近的点（已经加入最小生成树的节点不重复处理）
     * - 2.2 将其加入最小生成树
     * - 2.3 更新minDist（作为下一轮选择的参考，及更新当前选中的节点与剩余的节点的最短距离）
     */
    private int prim(int[][] grid, int n) {
        int pathSum = 0;

        // 定义数组：维护未选中节点到最小生成树的距离
        int[] minDist = new int[n];

        int maxVal = 10001; // Integer.MAX_VALUE
        Arrays.fill(minDist, maxVal); // 初始化：初始化最近距离为最大（或者结合题目设定max：10001）

        // 遍历每个节点，每次选中1个满足条件的节点加入最小生成树
        // boolean[] selected = new boolean[n]; // 表示当前节点的选中状态(为true表示已加入最小生成树)
        List<Integer> tree = new ArrayList<>();

        for (int x = 0; x < n; x++) {

            // ① 获取距离最小生成树的最近的未被选中的节点（即获取当前minDist中距离最小的那个节点）
            int cur = -1, curMin = maxVal + 1; // 推荐写法：初始化当前最近距离(处理初始minDist中的值都为maxVal的设定，确保可以选择一个节点)
            // int cur = 1, curMin = minDist[cur]; // 初始化当前最近距离(也可以默认为1，在后面的循环中会更新cur的值)
            for (int i = 0; i < n; i++) {
                if (!tree.contains(i) && minDist[i] < curMin) {
                    cur = i;
                    curMin = minDist[i];
                }
            }

            // ② 将选中节点加入最小生成树
            tree.add(cur);

            // ③ 更新当前选中节点与非生成树节点（剩余节点）的最短距离
            for (int i = 0; i < n; i++) {
                /*
                if (!tree.contains(i) && grid[cur][i] != 0 && grid[cur][i] < minDist[i]) { // 需注意此处需限定graph[cur][i]!=0表示只校验两个节点有直接路径的情况（无直接路径表示这两个节点不可达）
                    // 更新非生成树节点到最小生成树的最近距离 并 记录这个边关系
                    minDist[i] = grid[cur][i];
                }
                 */
                if (!tree.contains(i) && grid[cur][i] != 0) { // 需注意此处需限定graph[cur][i]!=0表示只校验两个节点有直接路径的情况（无直接路径表示这两个节点不可达）
                    // 更新非生成树节点到最小生成树的最近距离 并 记录这个边关系
                    minDist[i] = Math.min(minDist[i], grid[cur][i]);
                }
            }
        }

        // 当所有的节点都选出，构成一条完整路径，此时minDist存储的为最小生成树的最近距离、minDistEdge存储对应的边关系，累加总和得到最小生成树路径和
        for (int i = 0; i < n; i++) { // 从第2个节点开始计算路径
            pathSum += minDist[i];
        }

        return pathSum;

    }

    public int minimumCost(int n, int[][] connections) {
        int[][] grid = new int[n][n];
        // ① 遍历连接关系，转化为邻接矩阵
        for (int[] cn : connections) {
            int u = cn[0];
            int v = cn[1];
            int w = cn[2];
            // 构建无向图的邻接矩阵
            grid[u][v] = w;
            grid[v][u] = w;
        }

        // ② 调用算法，生成最小生成树
        int res = prim(grid, n);

        // 返回结果
        return res;
    }

}
