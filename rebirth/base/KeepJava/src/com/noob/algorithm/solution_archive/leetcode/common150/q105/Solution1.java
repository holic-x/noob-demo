package com.noob.algorithm.solution_archive.leetcode.common150.q105;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

/**
 * 105 从前序和中序遍历序列构造二叉树
 */
public class Solution1 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode node = buildTreeHelper(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
        return node;
    }

    /**
     * 思路：基于前序序列和中序序列进行构建
     * 遍历前序序列，从中序序列中获取关键的信息（例如inorder首节点为root）
     * 递归构建子树，需要限定的左右子树区间的边界
     */
    public TreeNode buildTreeHelper(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        // 1.递归出口：判断递归出口，基于前序序列进行遍历，如果其左右指针相遇则结束
        if (preLeft > preRight) {
            return null;
        }
        // 2.递归构建子树：构建根节点、构建左右节点
        TreeNode root = new TreeNode(preorder[preLeft]); // 根节点是前序遍历的第1个节点
        // 需计算该节点在中序序列的位置（索引下标）
        int idx = getRootIndex(root.val, inorder);
        // 递归构建子树
        root.left = buildTreeHelper(preorder, inorder, preLeft + 1, preLeft + idx - inLeft, inLeft, idx - 1);
        root.right = buildTreeHelper(preorder, inorder, preLeft + idx - inLeft + 1, preRight, idx + 1, inRight);
        // 返回创建的节点
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
