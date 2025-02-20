package com.noob.algorithm.plan_archive.plan01.day13;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟡106 从中序和后序遍历构造二叉树
 */
public class Solution106_01 {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        TreeNode root = builderHelper(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
        return root;
    }


    /**
     * 递归构建二叉树
     *
     * @param inorder     中序序列 LDR
     * @param postorder   后序序列 LRD
     * @param inL,inR
     * @param postL,postR
     */
    public TreeNode builderHelper(int[] inorder, int[] postorder, int inL, int inR, int postL, int postR) {
        // 递归出口
        if (postL > postR) {
            return null;
        }

        // 构建根节点(后序遍历的最后一个节点)
        int nodeVal = postorder[postR];
        TreeNode node = new TreeNode(nodeVal);
        // 获取节点在中序遍历序列的索引位置
        int midIndex = getIndex(inorder, nodeVal);
        // 左子数节点个数
        int leftTreeNum = (midIndex - 1) - inL;

        // 递归构建左右子树
        node.left = builderHelper(inorder, postorder, inL, midIndex - 1, postL, postL + leftTreeNum);
        node.right = builderHelper(inorder, postorder, midIndex + 1, inR, postL + leftTreeNum + 1, postR - 1);
        // 返回构建的节点
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
