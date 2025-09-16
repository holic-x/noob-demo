package com.noob.algorithm.daily.plan03.hot100_daily.day09.p028;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 337 打家劫舍III - https://leetcode.cn/problems/house-robber-iii/description/
 */
public class Solution337_02 {
    /**
     * 思路分析：递归判断处理
     */
    public int rob(TreeNode root) {
        return robDFS(root);
    }

    /**
     * 递归处理每个节点：对于每个节点可以选择偷或者不偷
     * 基于普通递归思路处理
     */
    private int robDFS(TreeNode node) {
        // 递归出口
        if (node == null) {
            return 0;
        }

        // 基于当前节点判断，选择偷或者不偷的方案

        // ① 偷当前节点，但是不能偷左、右子节点（即跳过左、右子节点的偷取，直接偷它们的子节点）
        int robCur = node.val; // 偷当前节点
        // 左子节点不为null，跳过左子节点的偷取，直接偷其子节点（leftNode.left、leftNode.right）
        TreeNode leftNode = node.left;
        if (leftNode != null) {
            robCur += (robDFS(leftNode.left) + robDFS(leftNode.right));
        }
        // 右子节点不为null，跳过右子树的偷取，直接偷其子节点（rightNode.left、rightNode.right）
        TreeNode rightNode = node.right;
        if (rightNode != null) {
            robCur += (robDFS(rightNode.left) + robDFS(rightNode.right));
        }

        // ② 不偷当前节点，则自由选择偷或者不偷左右子节点
        int skipCur = robDFS(leftNode) + robDFS(rightNode);

        // 返回基于当前节点的最大偷窃方案
        return Math.max(robCur, skipCur);
    }

}
