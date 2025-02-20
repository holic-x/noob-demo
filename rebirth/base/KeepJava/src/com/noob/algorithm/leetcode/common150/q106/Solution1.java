package com.noob.algorithm.leetcode.common150.q106;

import com.noob.algorithm.leetcode.common150.base.TreeNode;

/**
 * 106 从后序遍历和中序遍历序列构造二叉树
 */
public class Solution1 {

    /**
     * 基于后序序列构建（得到root），中序序列辅助（计算长度）
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTreeHelper(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    public TreeNode buildTreeHelper(int[] inorder, int[] postorder, int inL, int inR, int postL, int postR) {
        // 基于后序序列构建，当两个指针相遇则为递归出口
        if (postL > postR) {
            return null;
        }

        // 根据root节点获取其在inorder下的索引位置，以确定左右子树的节点个数
        int idx = getRootIndex(postorder[postR], inorder);
        int leftSize = idx - inL;

        // 构建节点：root、left、right
        TreeNode root = new TreeNode(postorder[postR]);
        root.left = buildTreeHelper(inorder, postorder, inL, idx - 1, postL, postL + leftSize - 1);
        root.right = buildTreeHelper(inorder, postorder, idx + 1, inR, postL + leftSize, postR - 1);

        // 返回构建的节点
        return root;
    }

    public int getRootIndex(int target, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == target) {
                return i;
            }
        }
        return -1;
    }

}
