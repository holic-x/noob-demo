package com.noob.algorithm.dmsxl.leetcode.q226;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

/**
 * 226 翻转二叉树
 */
public class Solution2 {
    // 思路：递归法
    public TreeNode invertTree(TreeNode root) {
        invertNode(root);
        return root;
    }

    public void invertNode(TreeNode node){
        // 递归出口
        if (node == null) {
            return ;
        }
        // 递归过程
        // 交换左右节点
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;

        // 继续递归交换左右子节点
        invertNode(node.left);
        invertNode(node.right);
    }
}
