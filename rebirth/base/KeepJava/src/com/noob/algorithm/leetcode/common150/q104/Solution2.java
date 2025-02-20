package com.noob.algorithm.leetcode.common150.q104;

import com.noob.algorithm.leetcode.common150.base.TreeNode;

/**
 * 104 二叉树的最大深度
 */
public class Solution2 {

    /**
     * 思路：递归法
     * 递归计算左右子树的最大深度，直到叶子节点（叶子结点作为子树其深度为0）
     */
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        // 分别递归计算左右子树的最大深度
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        // 获取到当前子树的最大深度
        return Math.max(leftHeight,rightHeight) + 1;
    }
}
