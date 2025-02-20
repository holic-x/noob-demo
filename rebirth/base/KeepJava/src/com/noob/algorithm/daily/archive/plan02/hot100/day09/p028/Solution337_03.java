package com.noob.algorithm.daily.archive.plan02.hot100.day09.p028;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟡 337 打家劫舍III - https://leetcode.cn/problems/house-robber-iii/description/
 */
public class Solution337_03 {
    /**
     * 思路分析：除了root之外，其他节点呈现父子相连，如果两个直接相连的房子同时被偷则会告警
     */
    public int rob(TreeNode root) {
        // 调用递归方案
        int[] val = dfs(root);
        return Math.max(val[0], val[1]);
    }

    /**
     * 基于递归思路处理
     * 对于每个节点有两种状态，偷、不偷,记录每个节点两种状态下可偷窃的最大金额
     */
    private int[] dfs(TreeNode node) {
        // 递归出口
        if (node == null) {
            return new int[]{0, 0};
        }

        // LRD 遍历顺序

        // 递归处理左节点
        int[] left = dfs(node.left);

        // 递归处理右节点
        int[] right = dfs(node.right);

        // 处理D节点
        int[] dp = new int[2]; // dp[0] 不偷该节点的情况下所获得的最大金额，dp[1] 偷该节点的情况下可获得的最大金额

        // 不偷该节点：考虑左右孩子的偷窃方案（可偷可不偷）
        dp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // 偷该节点：偷该节点，就不能偷左右孩子
        dp[1] = node.val + left[0] + right[0];

        // 返回
        return dp;
    }
}
