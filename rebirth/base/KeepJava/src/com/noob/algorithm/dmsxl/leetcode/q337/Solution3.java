package com.noob.algorithm.dmsxl.leetcode.q337;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

/**
 * 337 打家劫舍III
 */
public class Solution3 {
    /**
     * 遍历每个节点选择偷或者不偷(dfs)
     * 动态规划+递归思路
     */
    public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }

    // 返回值为一个dp[]数组，长度为2
    public int[] dfs(TreeNode node) {
        // dp数组定义:dp[0]表示不偷、dp[1]表示偷
        int[] dp = new int[2];

        if (node == null) { /// 递归出口
            return new int[]{0, 0}; // dp初始化
        }

        // 递归处理左节点（获取偷、不偷的最大金额）
        int[] left = dfs(node.left);

        // 递归处理右节点（获取偷、不偷的最大金额）
        int[] right = dfs(node.right);

        // 当前节点不偷：则递归计算左孩子、右孩子可能的方案，Max(左孩子不偷，左孩子偷) + Max(右孩子不偷，右孩子偷)
        dp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // 偷：左孩子不偷、右孩子不偷、当前节点偷
        dp[1] = node.val + left[0] + right[0];

        return dp;
    }
}
