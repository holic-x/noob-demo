package com.noob.algorithm.solution_archive.dmsxl.leetcode.q222;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

/**
 * 完全二叉树的节点个数（222）
 */
public class Solution2 {

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root);
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 判断当前节点的子树（完全二叉树）是否为满二叉树，如果是则无需递归，可通过公式计算
        int leftDepth = 0;
        TreeNode curLeft = node.left;
        while (curLeft != null) {
            leftDepth++;
            curLeft = curLeft.left; // 向左
        }

        int rightDepth = 0;
        TreeNode curRight = node.right;
        while (curRight != null) {
            rightDepth++;
            curRight = curRight.right; // 向右
        }

        // 如果leftDepth==rightDepth，则说明当前子树为满二叉树，直接通过公式计算返回节点个数
        if (leftDepth == rightDepth) {
            return (2 << leftDepth) - 1; // 注意(2<<1) 相当于2^2，所以leftDepth初始为0
        }

        // 如果leftDepth!=rightDepth，则通过递归方式计算节点个数
        int leftCnt = dfs(node.left);
        int rightCnt = dfs(node.right);
        return leftCnt + rightCnt + 1;
    }

}
