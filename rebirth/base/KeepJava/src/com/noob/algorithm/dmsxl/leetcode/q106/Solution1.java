package com.noob.algorithm.dmsxl.leetcode.q106;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

/**
 * 106 从前序和中序遍历构造二叉树
 */
public class Solution1 {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        TreeNode node = buildHelper(postorder, inorder, 0, postorder.length - 1, 0, inorder.length - 1);
        return node;
    }

    /**
     * @param postorder 后序序列 及构建范围 [postL,postR]
     * @param inorder   中序序列 及构建范围 [inL,inR]
     * @return
     */
    public TreeNode buildHelper(int[] postorder, int[] inorder, int postL, int postR, int inL, int inR) {

        // 基于后序序列进行构建
        if (postL > postR) {
            return null;
        }

        /**
         * 指定构建范围有效，则构建节点（后序序列的最后一个节点是root）
         * 中序：L[inL,idx-1]             R[idx+1,inR]
         * 后序：L[postL,postL+leftLen-1]   R[postL+leftLen,postR-1]
         */
        TreeNode root = new TreeNode(postorder[postR]);
        // 获取根节点在中序序列中的索引位置及左区间的元素个数
        int idx = getInorderIdx(inorder, postorder[postR]);
        int leftLen = idx - inL;

        // 递归构建左右子树
        root.left = buildHelper(postorder, inorder, postL, postL + leftLen - 1, inL, idx - 1);
        root.right = buildHelper(postorder, inorder, postL + leftLen, postR - 1, idx + 1, inR);

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
