package com.noob.algorithm.daily.plan03.hot100_random.day03.p006;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 111 二叉树的最小深度 - https://leetcode.cn/problems/minimum-depth-of-binary-tree/description/
 */
public class Solution111_02 {

    /**
     * 思路分析：最小深度（根节点到最近的叶子节点的最短路径上的节点数量）
     * 递归思路
     */
    public int minDepth(TreeNode root) {
        int ans = dfs(root);
        return ans;
    }


    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 根据节点的左右子节点是否为空分情况讨论
        if (node.left == null && node.right == null) {
            return 1;
        }

        if ((node.left == null && node.right != null) || (node.right == null && node.left != null)) {
            return dfs(node.left) + dfs(node.right) + 1;
        }

        if (node.left != null && node.right != null) {
            return Math.min(dfs(node.left), dfs(node.right)) + 1;
        }

        return -1;

    }


}
