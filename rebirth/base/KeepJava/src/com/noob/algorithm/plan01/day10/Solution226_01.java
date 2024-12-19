package com.noob.algorithm.plan01.day10;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

/**
 * 🟢226 翻转二叉树
 */
public class Solution226_01 {

    /**
     * 翻转二叉树：
     * 递归法：交换节点的左右节点
     */
    public TreeNode invertTree(TreeNode root) {
        // 调用递归方法处理翻转
        dfs(root);
        return root;
    }

    // dfs方法处理
    public void dfs(TreeNode node) {
        // 递归出口
        if (node == null) {
            return;
        }
        // 交换节点的左右子树
        TreeNode tempNode = node.left;
        node.left = node.right;
        node.right = tempNode;
        // 递归处理左右子树
        dfs(node.left);
        dfs(node.right);
    }
}
