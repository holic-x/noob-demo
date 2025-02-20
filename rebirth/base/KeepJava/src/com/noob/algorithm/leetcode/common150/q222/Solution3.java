package com.noob.algorithm.leetcode.common150.q222;

import com.noob.algorithm.leetcode.common150.base.TreeNode;

/**
 * 222 完全二叉树的节点个数
 */
public class Solution3 {

    /**
     * 针对完全二叉树的解法：
     * 深度
     */
    public int countNodes(TreeNode root) {
        // root 为null判断
        if (root == null) {
            return 0;
        }

        // 判断子树是否为满二叉树，如果不是则递归计算
        int leftDepth = 0, rightDepth = 0;
        // 计算左子树的深度
        TreeNode curLeft = root.left;
        while (curLeft != null) {
            leftDepth++;
            curLeft = curLeft.left;
        }

        // 计算右子树的深度
        TreeNode curRight = root.right;
        while (curRight != null) {
            rightDepth++;
            curRight = curRight.right;
        }

        // 判断左右子树的深度是否相同(如果递归向左向右深度相同则说明是满二叉树，则借助公式计算)
        if (leftDepth == rightDepth) {
            return (2 << leftDepth) - 1; // 注意(2<<1) 相当于2^2，所以leftDepth初始为0
        }

        // 如果左右子树深度不同，即非满二叉树则继续递归计算
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}
