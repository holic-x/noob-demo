package com.noob.algorithm.daily.plan03.hot100_daily.day03.p006;


import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 226 翻转二叉树 - https://leetcode.cn/problems/invert-binary-tree/description/
 */
public class Solution226_01 {

    /**
     * 思路分析：给出root，翻转二叉树，并返回根节点
     */
    public TreeNode invertTree(TreeNode root) {

        // 调用方法
        dfs(root);

        return root;
    }

    // 递归处理
    private void dfs(TreeNode node) {
        if (node == null) {
            return; // 递归出口
        }

        // node != null , 交换其左右子节点（反转概念）
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;

        // 递归处理左右子树
        dfs(node.left);
        dfs(node.right);
    }

}
