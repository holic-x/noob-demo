package com.noob.algorithm.dmsxl.leetcode.q111;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

/**
 * 111 二叉树的最小深度
 */
public class Solution3 {

    /**
     * 递归法
     */
    public int minDepth(TreeNode node) {
        //  node 为0的情况讨论
        if (node == null) {
            return 0;
        }
        // node 不为0的情况讨论（其left、right是否为null）
        int L = minDepth(node.left);
        int R = minDepth(node.right);

        // L == 0 || R == 0 左节点或者右节点为空
        if (L == 0 || R == 0) {
            return L == 0 ? R + 1 : L + 1;
//            return L + R + 1;
//            return Math.max(L, R) + 1;
        }
        // L != 0 && R != 0 左右节点都不为空
        return Math.min(L, R) + 1;
    }

}
