package com.noob.algorithm.daily.archive.plan02.day05.p012;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟢 530 二叉搜索树的最小绝对差 - https://leetcode.cn/problems/minimum-absolute-difference-in-bst/submissions/599208140/
 */
public class Solution530_01 {

    private long preNodeVal = Long.MAX_VALUE; // 上一个遍历节点值
    private long minSubVal = Long.MAX_VALUE; // 最小节点差值

    public int getMinimumDifference(TreeNode root) {
        // 调用递归算法
        dfs(root);
        // 返回结果
        return (int) minSubVal;
    }

    // 递归思路
    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        // LDR
        dfs(node.left);

        // 计算节点差值，并更新minSubVal
        minSubVal = Math.min(minSubVal, Math.abs(node.val - preNodeVal));
        preNodeVal = node.val; // 更新preNodeVal

        dfs(node.right);
    }

}
