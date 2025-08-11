package com.noob.algorithm.daily.plan03.hot100_daily.day04.p011;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 105 从前序和中序遍历序列构造二叉树 - https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class Solution105_01 {

    /**
     * 思路分析：
     * 前序遍历：DLR 前序确定D节点在头部
     * 中序遍历：LDR 中序确定左右子树
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = buildHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        return root;
    }


    /**
     * 辅助构建方法定义
     *
     * @param preorder 前序遍历序列数据
     * @param pLeft
     * @param pRight
     * @param inorder  中序遍历序列数据
     * @param iLeft
     * @param iRight
     * @return 构建范围（闭区间定义）
     */
    private TreeNode buildHelper(int[] preorder, int pLeft, int pRight, int[] inorder, int iLeft, int iRight) {
        if (pLeft > pRight) {
            return null;
        }

        // 构建节点
        int nodeVal = preorder[pLeft];
        TreeNode node = new TreeNode(nodeVal);

        // 获取D节点在中序遍历序列中的位置，进而划分其左右子树的构建
        int idx = getIdx(nodeVal, inorder);

        /**
         * preorder：[],inorder:[]
         * pLeft ,pLeft+1,......pLeft+leftNodeCnt,pLeft+leftNodeCnt+1...... pRight
         * iLeft ......idx-1,idx,idx+1...... iRight
         */
        // 递归构建左右子树
        int leftNodeCnt = idx - iLeft; // idx - 1 - iLeft + 1
        node.left = buildHelper(preorder, pLeft + 1, pLeft + leftNodeCnt, inorder, iLeft, idx - 1);
        node.right = buildHelper(preorder, pLeft + leftNodeCnt + 1, pRight, inorder, idx + 1, iRight);

        // 返回构建的节点
        return node;
    }

    private int getIdx(int val, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            if (val == inorder[i]) {
                return i; // 返回指定值在inorder序列中的位置
            }
        }
        // 未找到该元素
        return -1;
    }


}
