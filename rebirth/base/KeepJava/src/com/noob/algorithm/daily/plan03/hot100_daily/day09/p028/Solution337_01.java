package com.noob.algorithm.daily.plan03.hot100_daily.day09.p028;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 337 打家劫舍III - https://leetcode.cn/problems/house-robber-iii/description/
 */
public class Solution337_01 {
    /**
     * 思路分析：
     */
    public int rob(TreeNode root) {

        int[] dp = robMax(root);

        return Math.max(dp[0], dp[1]);
    }

    /**
     * 递归处理每个节点：对于每个节点可以选择偷或者不偷，定义二维数据记录每个节点偷或者不偷的状态int[]
     * - 偷：则不能偷子节点
     * - 不偷：则偷子节点
     * int[]{偷状态，不偷状态}
     */
    private int[] robMax(TreeNode node) {
        // 递归出口
        if (node == null) {
            return new int[]{0, 0};
        }

        // LRD

        // 处理左子树
        int[] leftNode = robMax(node.left);
        // 处理右子树
        int[] rightNode = robMax(node.right);

        // 处理D：处理偷、不偷的方案
        // 偷当前节点,不能偷左、右（当前节点的最大偷窃方案应该是当前节点+左右子树节点都不偷）
        int rob = node.val + leftNode[1] + rightNode[1];
        // 不偷当前节点：考虑左右孩子的偷窃方案（可偷可不偷）
        int unRob = Math.max(leftNode[0], leftNode[1]) + Math.max(rightNode[0], rightNode[1]);

        // 返回偷窃方案状态记录
        return new int[]{rob, unRob};

    }

}
