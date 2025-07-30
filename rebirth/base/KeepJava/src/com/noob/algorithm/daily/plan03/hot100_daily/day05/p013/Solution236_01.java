package com.noob.algorithm.daily.plan03.hot100_daily.day05.p013;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 236 二叉树的最近公共祖先 - https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/
 */
public class Solution236_01 {

    /**
     * 思路分析：
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root, p, q);
    }

    // 递归思路
    private TreeNode dfs(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null || node == p || node == q) {
            return node; // LCA 可能是自身，null情况下作为递归出口
        }

        // LRD处理顺序
        TreeNode findLeft = dfs(node.left, p, q);
        TreeNode findRight = dfs(node.right, p, q);

        // 左、右子树分情况讨论
        if (findLeft == null && findRight == null) {
            return null;
        } else if (findLeft != null && findRight != null) {
            return node; // 左右子树都找到了LCA 说明当前节点即为LCA
        } else {
            return findLeft == null ? findRight : findLeft;
        }

    }

}
