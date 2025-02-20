package com.noob.algorithm.plan_archive.plan02.hot100.day04.p009;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 112 路径总和
 */
public class Solution112_02 {

    private int curPathSum = 0;

    /**
     * 思路分析：判断是否存在路径和（根节点到叶子节点）为targetSum的路径
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        curPathSum = root.val;
        return dfs(root, targetSum);
    }

    // 递归遍历求路径和
    private boolean dfs(TreeNode node, int targetSum) {
        // 递归出口
        if (node == null) {
            return true;
        }

        if (node.left == null && node.right == null) {
            return curPathSum == targetSum;
        }

        // 递归回溯处理
        if (node.left != null) {
            curPathSum += node.left.val;
            if (dfs(node.left, targetSum)) { // 如果存在一条满足的路径则返回true
                return true;
            }
            curPathSum -= node.left.val;
        }

        if (node.right != null) {
            curPathSum += node.right.val;
            if (dfs(node.right, targetSum)) { // 如果存在一条满足的路径则返回true
                return true;
            }
            curPathSum -= node.right.val;
        }

        return false;
    }

}
