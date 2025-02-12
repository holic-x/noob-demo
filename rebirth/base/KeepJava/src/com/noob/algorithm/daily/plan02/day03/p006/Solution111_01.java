package com.noob.algorithm.daily.plan02.day03.p006;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟢 111 二叉树的最小深度 - https://leetcode.cn/problems/minimum-depth-of-binary-tree/description/
 */
public class Solution111_01 {

    /**
     * 思路分析：最小深度（根节点到最近的叶子节点的最短路径上的节点数量）
     * 基于递归思路，
     */
    public int minDepth(TreeNode root) {
        int minDepth = dfs(root);
        return minDepth;
    }

    // 基于递归思路
    private int dfs(TreeNode node) {
        // 递归出口
        if (node == null) {
            return 0;
        }

        // 根据左右子树与null取值进行分类讨论
        TreeNode L = node.left;
        TreeNode R = node.right;

        // ① 如果L、R均为null，说明为叶子节点，则最小深度为1
        if (L == null && R == null) {
            return 1;
        }

        // ② 如果L、R中只有一个为null（非叶子节点），则选择不为null的那部分继续递归
        if ((L == null && R != null) || (L != null && R == null)) {
            return Math.max(dfs(L), dfs(R)) + 1; // 为null的那个分支深度为0，因此此处简化选择max
        }

        // ③ 如果L、R中均不为null，则选择较小深度的进行递归遍历
        if (L != null && R != null) {
            return Math.min(dfs(L), dfs(R)) + 1;
        }
        return -1;
    }
}
