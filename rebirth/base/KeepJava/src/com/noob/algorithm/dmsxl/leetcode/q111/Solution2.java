package com.noob.algorithm.dmsxl.leetcode.q111;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

/**
 * 111 二叉树的最小深度
 */
public class Solution2 {

    /**
     * 递归法
     */
    public int minDepth(TreeNode node) {
        /**
         * 递归出口的三种情况：
         * 1.node 为 null，返回 0
         * 2.node 不为 null
         * - 2.1 node.left 和 node.right 两者均为 null 说明到达叶子节点，因此返回 1
         * - 2.2 node.left 和 node.right 中其中一个为null，说明node不是叶子节点，需继续递归遍历
         *      - 此时递归那个不为null的子节点，也就是返回深度较大的节点+1，也可以理解为l+r+1（l、r其中一个肯定为0）
         * - 2.3 node.left 和 node.right 两个都不为null，则返回左右子节点最小的深度+1
         */
        //  node 为0的情况讨论
        if (node == null) {
            return 0;
        }
        // node 不为0的情况讨论（其left、right是否为null）
        // 1.left、right均为null，到了叶子结点
        if (node.left == null && node.right == null) {
            return 1;
        }
        /**
         * 2.left、right中其中一个为null，非叶子结点，需继续遍历非叶子节点的情况(即max{minDepth(left),minDepth(right)} + 1)
         * 即 可以理解为 要继续左右节点 中选择不为 null 的那个节点进行递归获取最小深度
         * 也可以简化为 max{minDepth(left),minDepth(right)} + 1  或者 minDepth(left),minDepth(right) + 1 因为肯定是 0 + x + 1 的形式
         */
        if (node.left == null || node.right == null) {
            return Math.max(minDepth(node.left), minDepth(node.right)) + 1;
        }

        // 3.left、right都不为null，选择两者中最小深度+1
        return Math.min(minDepth(node.left), minDepth(node.right)) + 1;
    }

}
