package com.noob.algorithm.daily.plan01.archive.day13;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟢617 合并二叉树
 */
public class Solution617_01 {

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        TreeNode root = buildHelper(root1, root2);
        return root;
    }

    // 递归合并二叉树
    public TreeNode buildHelper(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return null;
        }
        if (node1 == null && node2 != null) {
            return node2;
        }
        if (node1 != null && node2 == null) {
            return node1;
        }

        // 构建节点
        TreeNode node = new TreeNode(node1.val + node2.val);
        node.left = buildHelper(node1.left, node2.left);
        node.right = buildHelper(node1.right, node2.right);

        // 返回节点
        return node;
    }

}
