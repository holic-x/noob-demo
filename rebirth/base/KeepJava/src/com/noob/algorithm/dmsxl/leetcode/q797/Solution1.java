package com.noob.algorithm.dmsxl.leetcode.q797;

import java.util.ArrayList;
import java.util.List;

/**
 * 797 所有可能的路径（LeetCode模式）
 */
public class Solution1 {

    List<List<Integer>> res = new ArrayList<>(); // 存储所有路径结果
    List<Integer> path = new ArrayList<>(); // 存放当前路径

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        path.add(0); // 所有路径起点均为0
        dfs(graph, 0, graph.length - 1); // 此处区间为[0,n-1]
        return res; // 返回结果
    }

    /**
     * 深度优先遍历
     *
     * @param graph 邻接矩阵
     * @param x     当前遍历的节点
     * @param n     终点
     */
    public void dfs(int[][] graph, int x, int n) {
        // 递归出口：当前遍历的节点x到达n，说明遍历到终点，找到一条可达路径
        if (x == n) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 回溯处理
        for (int i : graph[x]) { // 遍历节点x链接的所有节点(graph[x]中存储的即为x链接的所有节点)
            // 1.加入
            path.add(i);
            // 2.递归
            dfs(graph, i, n);
            // 3.回溯（恢复现场）
            path.remove(path.size() - 1);
        }
    }
}
