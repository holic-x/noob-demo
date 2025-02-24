package com.noob.algorithm.plan_archive.plan01.day10;

/**
 * 🟢559 N叉树的最大深度
 */
public class Solution559_03 {

    // DFS 方式
    public int maxDepth(Node root) {
        return dfs(root);
    }

    // 计算节点深度
    public int dfs(Node node) {
        // 递归出口
        if (node == null) {
            return 0;
        }
        // 递归计算每个子节点的深度，选择最大的深度
        int max = 0;
        for (Node child : node.children) {
            max = Math.max(max, dfs(child));
        }
        // 返回节点最大深度
        return max + 1;
    }
}


