package com.noob.algorithm.daily.plan03.hot100_daily.day04.p008;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 222 完全二叉树的节点个数 - https://leetcode.cn/problems/count-complete-tree-nodes/description/
 */
public class Solution222_03 {

    /**
     * 思路分析：
     * 利用完全二叉树的特性：
     * 因此基于dfs思路的处理，可以区分满二叉树和普通二叉树的不同的统计
     * - 满二叉树：左右子树深度相同，节点个数为2^h^-1
     * - 普通二叉树：L+R+1
     */
    public int countNodes(TreeNode root) {
        return dfs(root);
    }

    // 递归求解节点个数：L+R+1
    private int dfs(TreeNode node) {
        // 递归出口
        if (node == null) {
            return 0;
        }

        // node 不为null,根据是否为满二叉树决定节点统计方式
        // 左指针
        TreeNode lt = node.left;
        int leftDepth = 0;
        while (lt != null) {
            leftDepth++;
            lt = lt.left;
        }

        // 右指针
        TreeNode rt = node.right;
        int rightDepth = 0;
        while (rt != null) {
            rightDepth++;
            rt = rt.right;
        }

        // 校验左、右高度是否相同，区分不同处理场景
        if (leftDepth == rightDepth) {
            // 如果相同则说明是满二叉树,可通过节点公式计算处理
            return (2 << leftDepth) - 1;
        } else {
            // 如果不相同说明是普通二叉树，继续递归左右子节点
            return dfs(node.left) + dfs(node.right) + 1;
        }
    }

}
