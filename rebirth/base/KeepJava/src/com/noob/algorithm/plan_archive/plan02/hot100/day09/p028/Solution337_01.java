package com.noob.algorithm.plan_archive.plan02.hot100.day09.p028;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 337 打家劫舍III - https://leetcode.cn/problems/house-robber-iii/description/
 */
public class Solution337_01 {
    /**
     * 思路分析：除了root之外，其他节点呈现父子相连，如果两个直接相连的房子同时被偷则会告警
     */
    public int rob(TreeNode root) {
        // 调用递归方案
        return dfs(root);
    }

    /**
     * 基于递归思路处理
     * 对于每个节点有两种状态，偷、不偷
     * ① 如果偷当前节点：则不偷其子节点（跳过其子节点，选择考虑其孙子节点的处理）
     * ② 如果不偷：则偷其子节点
     */
    private int dfs(TreeNode node) {
        // 递归出口
        if (node == null) {
            return 0;
        }
        // 如果为叶子节点
        if (node.left == null && node.right == null) {
            return node.val;
        }

        // 选择是否要偷该节点，分情况讨论
        // ① 偷该节点，则跳过其子节点，考虑孙子节点
        int val1 = node.val;
        if (node.left != null) {
            val1 += (dfs(node.left.left) + dfs(node.left.right));
        }
        if (node.right != null) {
            val1 += (dfs(node.right.left) + dfs(node.right.right));
        }

        // ② 不偷该节点，则考虑子节点
        int val2 = dfs(node.left) + dfs(node.right);

        // 返回两种方案的最大偷窃金额
        return Math.max(val1, val2);
    }
}
