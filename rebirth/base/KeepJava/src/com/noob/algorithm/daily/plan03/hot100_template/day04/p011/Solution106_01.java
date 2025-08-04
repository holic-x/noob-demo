package com.noob.algorithm.daily.plan03.hot100_template.day04.p011;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 106 从中序和后序遍历序列构造二叉树 - https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
 */
public class Solution106_01 {

    /**
     * 思路分析：
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        TreeNode root = buildHelper(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
        return root;
    }

    /**
     * 构建辅助
     * 中序：LDR
     * 后序：LRD
     *
     * @param inorder
     * @param postorder
     * @param inL
     * @param inR
     * @param pL
     * @param pR
     * @return
     */
    private TreeNode buildHelper(int[] inorder, int[] postorder, int inL, int inR, int pL, int pR) {
        if (pL > pR) {
            return null;
        }
        // 构建节点
        int nodeVal = postorder[pR];
        TreeNode node = new TreeNode(nodeVal);

        int idx = getIdx(nodeVal, inorder);
        /**
         * LDR:inL.......idx-1,idx.idx+1........inR
         * LRD:pL.....pL+leftNodeCnt-1,pl+leftNodeCnt...........pR-1,pR
         * leftNodeCnt = （idx-1）-inL + 1 = idx - inL
         */
        int leftNodeCnt = idx - inL;
        node.left = buildHelper(inorder, postorder, inL, idx - 1, pL, pL + leftNodeCnt - 1);
        node.right = buildHelper(inorder, postorder, idx + 1, inR, pL + leftNodeCnt, pR - 1);

        // 返回构建节点
        return node;
    }


    private int getIdx(int val, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            if (val == inorder[i]) {
                return i;
            }
        }
        return -1;
    }

}
