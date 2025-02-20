package com.noob.algorithm.daily.archive.plan02.hot100.day04.p010;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟢 617 合并二叉树 - https://leetcode.cn/problems/merge-two-binary-trees/description/
 */
public class Solution617_01 {

    /**
     * 思路分析：合并二叉树
     * DFS(递归遍历)
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        TreeNode root = mergeHelper(root1, root2);
        return root;
    }

    // 递归合并
    private TreeNode mergeHelper(TreeNode node1, TreeNode node2) {
        // ① 如果node1、node2均为null，返回null
        if (node1 == null && node2 == null) {
            return null;
        }
        // ② 如果node1、node2中有一个不为null，返回非空的节点
        if ((node1 != null && node2 == null) || (node1 == null && node2 != null)) {
            return node1 == null ? node2 : node1;
        }

        // ③ 如果node1、node2均不为null，则需进行合并
        TreeNode node = new TreeNode(node1.val + node2.val);
        node.left = mergeHelper(node1.left, node2.left);
        node.right = mergeHelper(node1.right, node2.right);
        // 返回合并后的节点
        return node;
    }

}
