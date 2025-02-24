package com.noob.algorithm.plan_archive.plan01.day14;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢530 二叉搜索树的最小绝对差
 */
public class Solution530_01 {

    public long preVal = Long.MAX_VALUE; // 记录当前遍历节点的上一个节点值（基于中序遍历）
    public long minSubVal = Long.MAX_VALUE; // 记录最小绝对差值

    public int getMinimumDifference(TreeNode root) {

        // 调用递归算法处理
        dfs(root);

        return (int) minSubVal;
    }

    // 基于中序遍历进行校验，获取最小绝对差
    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        // 递归处理左节点
        dfs(node.left);

        // 处理节点
        if (preVal != Integer.MAX_VALUE) {
            minSubVal = Math.min(minSubVal, Math.abs(node.val - preVal));
        }
        preVal = node.val;

        // 递归处理右节点
        dfs(node.right);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(0);
        TreeNode node3 = new TreeNode(48);
        TreeNode node4 = new TreeNode(12);
        TreeNode node5 = new TreeNode(49);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        Solution530_01 s = new Solution530_01();
        s.getMinimumDifference(node1);
    }

}
