package com.noob.algorithm.daily.codeTop;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 110 平衡二叉树 - https://leetcode.cn/problems/balanced-binary-tree/description/
 */
public class Solution110_01 {

    /**
     * 平衡二叉树：左右子树的最大高度差不超过1
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 递归校验左右子树的最大高度差
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return Math.abs(leftHeight - rightHeight) <= 1;
    }

    public int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 递归计算左右子树的高度，返回当前节点高度
        return Math.max(height(node.left), height(node.right)) + 1;
    }

}
