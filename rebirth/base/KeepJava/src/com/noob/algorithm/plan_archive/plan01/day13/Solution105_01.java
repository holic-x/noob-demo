package com.noob.algorithm.plan_archive.plan01.day13;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡105 从中序和前序遍历构造二叉树
 */
public class Solution105_01 {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        TreeNode root = builderHelper(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
        return root;
    }


    /**
     * 递归构建二叉树
     *
     * @param preorder  前序序列 DLR
     * @param inorder   中序序列 LDR
     * @param preL,preR
     * @param inL,inR
     */
    public TreeNode builderHelper(int[] preorder, int[] inorder, int preL, int preR, int inL, int inR) {
        // 递归出口
        if (preL > preR) {
            return null;
        }

        // 构建节点（前序遍历的第1个元素）
        int nodeVal = preorder[preL];
        TreeNode node = new TreeNode(nodeVal);
        // 获取节点在前序序列的索引位置
        int midIdx = getIndex(inorder, nodeVal);
        // 计算左子树节点个数
        int leftTreeNum = (midIdx - 1) - inL;

        // 递归构建左右子树
        node.left = builderHelper(preorder, inorder, preL + 1, preL + 1 + leftTreeNum, inL, midIdx - 1);
        node.right = builderHelper(preorder, inorder, preL + 1 + leftTreeNum + 1, preR, midIdx + 1, inR);
        // 返回构建好的节点
        return node;
    }

    public int getIndex(int[] inorder, int target) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == target) {
                return i;
            }
        }
        return -1;
    }

}
