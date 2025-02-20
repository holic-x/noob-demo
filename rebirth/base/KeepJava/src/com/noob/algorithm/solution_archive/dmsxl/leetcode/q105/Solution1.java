package com.noob.algorithm.solution_archive.dmsxl.leetcode.q105;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

/**
 * 105 从前序和中序遍历构造二叉树
 */
public class Solution1 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode node = buildHelper(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
        return node;
    }

    /**
     * 辅助构造方法
     *
     * @param preorder 前序序列 指定构造索引范围 [preL,preR]
     * @param inorder  中序序列 指定构造索引范围 [inL,inR]
     */
    public TreeNode buildHelper(int[] preorder, int[] inorder, int preL, int preR, int inL, int inR) {

        // 基于前序序列进行构建
        if (preL > preR) {
            return null;
        }

        /**
         * 如果构建索引范围有效，则进行构建，取前序序列的第一个节点作为根节点进行构建
         * 中序：L[inL,idx-1]             R[preL+leftLen,idx - 1]
         * 前序：L[preL+leftLen+1,preR]   R[idx+1,inR]
         */
        TreeNode root = new TreeNode(preorder[preL]);

        // 得到当前root节点在中序遍历中的索引位置，并计算左区间的元素个数
        int idx = getInorderIdx(inorder, preorder[preL]);
        int leftLen = idx - inL; // [inL,idx-1]内的节点个数

        // 递归构建左、右子树
        root.left = buildHelper(preorder, inorder, preL + 1, preL + leftLen, inL, idx - 1);
        root.right = buildHelper(preorder, inorder, preL + leftLen + 1, preR, idx + 1, inR);

        // 返回构建的节点
        return root;
    }

    // 获取指定元素在中序序列中索引的位置
    public int getInorderIdx(int[] inorder, int target) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == target) {
                return i;
            }
        }
        return -1;
    }

}