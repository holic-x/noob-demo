package com.noob.algorithm.solution_archive.dmsxl.leetcode.q404;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

/**
 * 404 左叶子之和
 */
public class Solution3 {

    // DFS 思路
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root);
    }

    // DFS(DLR)
    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftSum = 0;
        // 判断当前节点是否存在"左叶子"
        if (node.left != null && node.left.left == null && node.left.right == null) {
            // 存在左叶子：累加和
            leftSum = node.left.val;
        }

        return leftSum + dfs(node.left) + dfs(node.right);
    }
}
