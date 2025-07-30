package com.noob.algorithm.daily.plan03.hot100_daily.day03.p006;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 104 二叉树的最大深度 - https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/
 */
public class Solution104_01 {

    /**
     * 思路分析：
     * - 根节点->最远叶子节点的最长路径的节点数
     * - 递归思路：max{maxLeft,maxRight} + 1 深度优先算法
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return dfs(root);
    }


    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 分别计算左、右子节点的最大深度
        int leftDepth = dfs(node.left);
        int rightDepth = dfs(node.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }


}
