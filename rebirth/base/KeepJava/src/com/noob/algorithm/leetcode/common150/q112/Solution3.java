package com.noob.algorithm.leetcode.common150.q112;

import com.noob.algorithm.leetcode.common150.base.TreeNode;

/**
 * 112 路径之和
 */
public class Solution3 {

    boolean res = false; // 定义全局变量存储结果

    /**
     * 递归
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // root 为空
        if (root == null) {
            return false;
        }
        dfs(root, targetSum);
        return res;
    }

    /**
     * 深度优先遍历
     */
    public void dfs(TreeNode root, int targetSum) {
        // 递归出口
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null && root.val == targetSum) {
            res = true;
        }
        dfs(root.left, targetSum - root.val);
        dfs(root.right, targetSum - root.val);
    }
}
