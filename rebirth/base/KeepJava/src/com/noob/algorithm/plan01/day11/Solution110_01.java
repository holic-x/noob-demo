package com.noob.algorithm.plan01.day11;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

/**
 * 🟢110 平衡二叉树
 */
public class Solution110_01 {

    // DFS:平衡二叉树每个节点的左右子树高度差不超过1（超出1说明非平衡）
    public boolean isBalanced(TreeNode root) {
        return balance(root);
    }

    /**
     * 基于平衡二叉树的特性：核心思路是校验每个节点的左右子树的高度差是否大于1
     * 1.递归遍历每个节点，判断每个节点的左右子树的高度差是否大于1
     * 2.定义计算节点高度方法（即求节点的最大深度）
     */
    public boolean balance(TreeNode node) {
        if (node == null) {
            return true;
        }
        // 校验当前节点左右子树高度差是否大于1
        if (Math.abs(maxDepth(node.left) - maxDepth(node.right)) > 1) {
            return false;
        }
        // 继续递归校验
        return balance(node.left) && balance(node.right);
    }

    // 求节点的最大深度
    public int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftMaxDepth = maxDepth(node.left);
        int rightMaxDepth = maxDepth(node.right);
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }
}
