package com.noob.algorithm.plan_archive.plan02.hot100.day04.p011;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 105 从前序和中序遍历序列构造二叉树
 */
public class Solution105_01 {

    /**
     * 基于前序+中序构造二叉树
     * ① 前序（DLR）：区间范围内的第1个元素为D（即preL边界）
     * ② 中序：根据D的位置可确定左、右子树的节点个数，进而划分区间
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = buildHelper(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);
        return root;
    }

    // 递归构建
    private TreeNode buildHelper(int[] inorder, int inL, int inR, int[] preorder, int preL, int preR) {
        // 校验是否越界（基于前序构建）
        if (preL > preR) {
            return null;
        }
        // 构建节点
        TreeNode node = new TreeNode(preorder[preL]);
        int nodeIdx = getIdx(inorder, node.val);
        int leftLen = nodeIdx - inL;

        // 递归构建左右子树
        node.left = buildHelper(inorder, inL, nodeIdx - 1, preorder, preL + 1, leftLen + preL);
        node.right = buildHelper(inorder, nodeIdx + 1, inR, preorder, leftLen + preL + 1, preR);

        return node;
    }

    // 根据值获取索引
    private int getIdx(int[] inorder, int val) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == val) {
                return i;
            }
        }
        return -1;
    }
}
