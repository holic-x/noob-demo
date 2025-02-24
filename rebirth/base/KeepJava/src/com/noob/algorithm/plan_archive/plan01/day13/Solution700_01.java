package com.noob.algorithm.plan_archive.plan01.day13;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢700 二叉搜索树中的搜索
 */
public class Solution700_01 {

    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode findNode = dfs(root, val);
        return findNode;
    }

    // dfs 递归搜索
    public TreeNode dfs(TreeNode node, int target) {
        if (node == null) {
            return null;
        }

        if (node.val == target) {
            return node;
        }

        // 递归搜索左、右子树(利用二叉搜索树特性：左子树的节点小于右子树的节点)
        return (target < node.val) ? dfs(node.left, target) : dfs(node.right, target);
    }
}
