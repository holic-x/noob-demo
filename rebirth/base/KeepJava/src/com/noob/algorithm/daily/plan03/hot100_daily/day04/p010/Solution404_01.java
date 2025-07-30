package com.noob.algorithm.daily.plan03.hot100_daily.day04.p010;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 404 左叶子之和 - https://leetcode.cn/problems/sum-of-left-leaves/description/
 */
public class Solution404_01 {

    /**
     * 思路分析：给定二叉树的根节点 root ，返回所有左叶子之和
     * - 如果一个节点包括左叶子，则其满足：node.left != null && (node.left.left == null && node.left.right == null)
     */
    public int sumOfLeftLeaves(TreeNode root) {
        dfs(root);
        return leftLeafSum;
    }

    private int leftLeafSum = 0;

    // 递归法处理
    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        if (node.left != null && node.left.left == null && node.left.right == null) {
            leftLeafSum += node.left.val;
        }

        // 处理左右子树
        dfs(node.left);
        dfs(node.right);
    }


    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        Solution404_01 s = new Solution404_01();
        s.sumOfLeftLeaves(node);
    }

}
