package com.noob.algorithm.dmsxl.leetcode.q112;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

/**
 * 112 路径总和
 */
public class Solution3 {

    // DFS 递归 + 回溯
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return dfs(root, targetSum);
    }

    public boolean dfs(TreeNode node, int targetSum) {
        if (node == null) {
            return false;
        }

        // 遇到叶子节点（如果路径和匹配targetSum，则递归最终到叶子节点剩余值应该和当前遍历节点值一致才满足）
        if (node.left == null && node.right == null) {
            if (node.val == targetSum) {
                return true;
            }
        }

        // 递归判断左、右节点是否满足条件
        if (node.left != null) {
            targetSum = targetSum - node.val; // 递归，处理节点
            if (dfs(node.left, targetSum)) {
                return true;
            }
            targetSum = targetSum + node.val; // 回溯（复原现场）
        }
        if (node.right != null) {
            targetSum = targetSum - node.val; // 递归，处理节点
            if (dfs(node.right, targetSum)) {
                return true;
            }
            targetSum = targetSum + node.val; // 回溯（复原现场）
        }

        // 没有满足条件的内容
        return false;
    }

}
