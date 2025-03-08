package com.noob.algorithm.daily.codeTop;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

/**
 * 🟢 104 二叉树的最大深度 - https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/
 */
public class Solution104_01 {

    /**
     * 思路分析：
     * 节点的最大深度 = max{left,right} + 1
     */
    public int maxDepth(TreeNode root) {
        return dfs(root);
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0; // 叶子节点的最大深度为0
        }

        // 递归求左、右子树的最大深度
        int maxLeft = dfs(node.left);
        int maxRight = dfs(node.right);
        // 返回节点的最大深度
        return Math.max(maxLeft, maxRight) + 1;
    }

}
