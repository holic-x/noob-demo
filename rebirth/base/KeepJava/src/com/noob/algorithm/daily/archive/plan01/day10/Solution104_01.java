package com.noob.algorithm.daily.archive.plan01.day10;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

/**
 * 🟢 104 二叉树的最大深度
 */
public class Solution104_01 {

    /**
     * 递归法：
     * 当前树的高度（最大深度） = max{leftTree,rightTree} + 1
     * 即当前节点的高度为其左、右子节点的最大高度 + 1
     */
    public int maxDepth(TreeNode root) {
        int maxDepth = dfs(root);
        return maxDepth;
    }

    public int dfs(TreeNode node) {
        // 递归出口
        if (node == null) { // 叶子节点高度为0
            return 0;
        }
        // 递归计算左、右子节点的高度
        int leftTree = dfs(node.left);
        int rightTree = dfs(node.right);
        // 返回最大高度
        return Math.max(leftTree, rightTree) + 1;
    }
}
