package com.noob.algorithm.plan_archive.plan02.hot100.day03.p006;


import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 226 翻转二叉树 - https://leetcode.cn/problems/invert-binary-tree/description/
 */
public class Solution226_02 {

    /**
     * 思路分析：递归思路，基于递归处理交换左右子树
     */
    public TreeNode invertTree(TreeNode root) {
        // 调用递归算法
        invertTreeHelper(root);
        // 返回翻转后的树
        return root;
    }

    // 递归处理翻转
    private void invertTreeHelper(TreeNode node) {
        // 递归出口
        if (node == null) {
            return;
        }

        // 处理节点（交换左右字数）
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;

        // 递归处理
        invertTreeHelper(node.left); // 递归处理左子节点
        invertTreeHelper(node.right);// 递归处理右子节点
    }
}
