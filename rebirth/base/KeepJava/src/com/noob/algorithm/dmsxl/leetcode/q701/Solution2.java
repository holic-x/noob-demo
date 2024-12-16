package com.noob.algorithm.dmsxl.leetcode.q701;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

/**
 * 701 二叉树中的插入操作
 */
public class Solution2 {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        // 递归构建
        return buildHelper(root, val);
    }

    // 递归创建
    public TreeNode buildHelper(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val);
        }

        // 根据当前节点值和插入节点值进行判断
        if (node.val > val) {
            // 插入到左侧
            node.left = buildHelper(node.left, val);
        }

        if (node.val < val) {
            // 插入到右侧
            node.right = buildHelper(node.right, val);
        }
        return node;
    }

}
