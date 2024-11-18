package com.noob.algorithm.dmsxl.leetcode.q110;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

/**
 * 110 平衡二叉树
 */
public class Solution1 {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 当前节点的左子树和右子树高度差不超过1,超出1说明非平衡
        if (Math.abs(maxDepth(root.left) - maxDepth(root.right)) > 1) {
            return false;
        }
        // 递归判断其左右子树是否满足平衡二叉树特性
        return isBalanced(root.left) && isBalanced(root.right);
    }

    // 求节点的最大深度
    public int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 当前节点的最大深度：max{左子树的最大深度,右子树的最大深度}+1
        return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;

    }
}
