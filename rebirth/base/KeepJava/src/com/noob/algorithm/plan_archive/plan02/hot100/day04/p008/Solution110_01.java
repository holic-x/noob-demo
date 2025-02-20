package com.noob.algorithm.plan_archive.plan02.hot100.day04.p008;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 110 平衡二叉树 - https://leetcode.cn/problems/balanced-binary-tree/description/
 */
public class Solution110_01 {

    /**
     * 平衡二叉树的左右子树的最大高度差不超过1
     */
    public boolean isBalanced(TreeNode root) {
        return dfs(root);
    }

    // 递归校验平衡二叉树
    private boolean dfs(TreeNode node) {
        if (node == null) {
            return true;
        }

        // 校验左右子树的高度差是否大于1
        if (Math.abs(maxDepth(node.left) - maxDepth(node.right)) > 1) {
            return false;
        }

        // 校验左子树
        boolean isLeftBalanced = dfs(node.left);
        boolean isRightBalanced = dfs(node.right);

        // 平衡二叉树的左右子树也为平衡二叉树
        return isLeftBalanced && isRightBalanced;
    }


    // 计算节点的最大高度
    private int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 递归计算左子树的最大高度
        int leftMaxDepth = maxDepth(node.left);
        int rightMaxDepth = maxDepth(node.right);
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }

}
