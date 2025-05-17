package com.noob.algorithm.daily.plan03.hot100_random.day03.p006;


import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 226 翻转二叉树 - https://leetcode.cn/problems/invert-binary-tree/description/
 */
public class Solution226_01 {

    /**
     * 思路分析：
     */
    public TreeNode invertTree(TreeNode root) {
        dfs(root);
        return root;
    }

    // 递归处理
    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        // 翻转二叉树
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;

        // 递归处理
        dfs(node.left);
        dfs(node.right);
    }

}
