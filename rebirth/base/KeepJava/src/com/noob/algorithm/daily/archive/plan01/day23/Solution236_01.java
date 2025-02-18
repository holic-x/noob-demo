package com.noob.algorithm.daily.archive.plan01.day23;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟡 236 二叉树的最近公共祖先 - https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class Solution236_01 {


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root, p, q);
    }

    /**
     * 递归穷举思路
     */
    private TreeNode dfs(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return null;
        }
        if (node == p || node == q) {
            return node;
        }

        // 校验公共节点是在左子树还是右子树，或者是两个子树共有
        TreeNode findLeft = dfs(node.left, p, q);
        TreeNode findRight = dfs(node.right, p, q);
        if (findLeft != null && findRight != null) {
            return node; // 当前节点为最近的公共祖先
        }
        if (findLeft != null && findRight == null) {
            return findLeft;
        }
        if (findLeft == null && findRight != null) {
            return findRight;
        }
        return null;
    }

}
