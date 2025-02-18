package com.noob.algorithm.daily.archive.plan02.day05.p013;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟡 236 二叉树的最近公共祖先 - https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/
 */
public class Solution236_01 {

    /**
     * 思路分析：分类讨论
     * 1.递归出口：node为空节点、p、q三者之一则返回node
     * 2.其他情况则进一步根据递归子树来分情况讨论：findLeft、findRight
     * - findLeft、findRight 均为null，无公共节点=》返回null
     * - findLeft、findRight 均不为null，当前节点即为公共节点=》返回node
     * - findLeft、findRight 中只有一个为null，选择不为null的分支=》返回findLeft、findRight中非null的节点
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root, p, q);
    }

    // 递归思路
    private TreeNode dfs(TreeNode node, TreeNode p, TreeNode q) {
        // ① 如果节点为null或者到达p、q中的其中一个，则直接返回node
        if (node == null || node == p || node == q) {
            return node;
        }

        // ② 如果是其他情况，则需要递归从左、右子树中获取
        TreeNode findLeft = dfs(node.left, p, q);
        TreeNode findRight = dfs(node.right, p, q);

        // 根据findLeft、findRight的结果分情况讨论
        if (findLeft == null && findRight == null) {
            return null; // 左右子树都没有找到
        }
        if (findLeft != null && findRight != null) {
            return node; // 左右子树都找到了，当前节点node即为公共节点
        }
        // 返回不为null的那个子树
        return findLeft == null ? findRight : findLeft;
    }

}
