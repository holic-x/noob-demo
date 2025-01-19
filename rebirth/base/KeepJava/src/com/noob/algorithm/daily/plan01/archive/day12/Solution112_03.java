package com.noob.algorithm.daily.plan01.archive.day12;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟢112 路径之和
 */
public class Solution112_03 {

    public int pathSum = 0;

    // DFS + 回溯
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        // 初始化根节点加入路径
        pathSum += root.val;
        return dfs(root, targetSum);
    }

    public boolean dfs(TreeNode node, int targetSum) {
        if (node == null) {
            return false;
        }

        // 判断当前叶子结点值是否和targetSum一致
        if (node.left == null && node.right == null) {
            if (pathSum == targetSum) {
                return true;
            }
        }

        // 递归 + 回溯
        boolean validLeft = false, validRight = false;
        if (node.left != null) {
            pathSum += node.left.val;
            validLeft = dfs(node.left, targetSum);
            pathSum -= node.left.val;
        }
        if (node.right != null) {
            pathSum += node.right.val;
            validRight = dfs(node.right, targetSum);
            pathSum -= node.right.val;
        }
        // 左、右节点找到一条满足的路径即可
        return validLeft || validRight;
    }
}
