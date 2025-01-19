package com.noob.algorithm.daily.plan01.archive.day14;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟢530 二叉搜索树的最小绝对差
 */
public class Solution530_02 {

    public TreeNode preNode = null; // 记录当前遍历节点的上一个节点（基于中序遍历）

    public long minSubVal = Integer.MAX_VALUE; // 记录最小绝对差值

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
        if (preNode != null) {
            minSubVal = Math.min(minSubVal, Math.abs(node.val - preNode.val));
        }
        preNode = node; // 更新preNode

        // 递归处理右节点
        dfs(node.right);
    }

}
