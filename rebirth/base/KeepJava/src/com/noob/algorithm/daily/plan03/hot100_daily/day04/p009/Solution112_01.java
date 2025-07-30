package com.noob.algorithm.daily.plan03.hot100_daily.day04.p009;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 112 路径总和 - https://leetcode.cn/problems/path-sum/
 */
public class Solution112_01 {

    /**
     * 思路分析：求[根节点->叶子节点]路径上所有节点值之和等于targetSum的路径，存在则返回true，不存在则返回false
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return dfs(root, 0, targetSum);
    }

    // 递归处理校验路径节点值之和
    private boolean dfs(TreeNode node, int currentSum, int targetSum) {
        if (node == null) {
            return false;
        }
        // node 不为null，处理节点
        currentSum += node.val;

        // 校验当前节点是否为叶子节点，且路径和是否等于目标和
        if (node.left == null && node.right == null) {
            if (currentSum == targetSum) {
                return true;
            }
        }

        // 递归处理左右子树
        boolean validLeft = dfs(node.left, currentSum, targetSum);

        boolean validRight = dfs(node.right, currentSum, targetSum);

        // 只要有一个满足即可
        return validLeft || validRight;
    }

}
