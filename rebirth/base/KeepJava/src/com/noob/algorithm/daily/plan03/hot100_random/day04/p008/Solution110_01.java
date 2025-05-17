package com.noob.algorithm.daily.plan03.hot100_random.day04.p008;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 110 平衡二叉树 - https://leetcode.cn/problems/balanced-binary-tree/description/
 */
public class Solution110_01 {

    /**
     * 思路分析：
     * 平衡二叉树的特性：左右子树的最大高度差不超过1
     */
    public boolean isBalanced(TreeNode root) {
        // 遍历每个节点，如果左右子树最大高度差不超过1则视作平衡
        if (root == null) {
            return true;
        }

        int maxLeft = maxDepth(root.left);
        int maxRight = maxDepth(root.right);
        if (Math.abs(maxLeft - maxRight) > 1) {
            return false;
        }

        // 递归处理左、右子树
        return isBalanced(root.left) && isBalanced(root.right);
    }

    // 获取节点的最大高度
    private int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 递归获取左、右子树的最大高度
        int maxLeft = maxDepth(node.left);
        int maxRight = maxDepth(node.right);

        return Math.max(maxLeft, maxRight) + 1;
    }


}
