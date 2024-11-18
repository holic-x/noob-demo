package com.noob.algorithm.dmsxl.leetcode.q617;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

/**
 * 617 合并二叉树
 */
public class Solution2 {

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        return buildHelper(root1, root2);
    }

    public TreeNode buildHelper(TreeNode node1, TreeNode node2) {

        /**
         * 分三种情况讨论：
         * 1.node1、node2 都为null，合并后的节点也为null
         * 2.node1、node2 都不为null，合并后的节点是两个节点之和，相应需要递归构建左、右子节点
         * 3.node1、node2 其中一个为null，合并后的节点是不为null的那个(直接挂载)
         */
        // node1、node2 都为null
        if (node1 == null && node2 == null) {
            return null;
        }
        // node1为null，选择node2
        if (node1 == null) {
            return node2;
        }
        // node2为null，选择node1
        if (node2 == null) {
            return node1;
        }

        // node1、node2 都不为null，合并后的节点是两个节点之和，相应需要递归构建左、右子节点
        TreeNode mergeNode = new TreeNode(node1.val + node2.val);
        mergeNode.left = buildHelper(node1.left, node2.left);
        mergeNode.right = buildHelper(node1.right, node2.right);
        return mergeNode;
    }

}
