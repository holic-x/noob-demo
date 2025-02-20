package com.noob.algorithm.daily.archive.plan02.hot100.day03.p006;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.LinkedList;

/**
 * 🟢 104 二叉树的最大深度 - https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/
 */
public class Solution104_02 {

    /**
     * 思路分析：二叉树的最大深度（即树的高度）
     * 基于递归思路，对于每个节点的最大深度设定为：max{maxLeft,maxRight} + 1 (左、右子树中的最大深度加上1（自身节点），构成本节点maxDepth)
     */
    public int maxDepth(TreeNode root) {
        int maxDepth = dfs(root);
        return maxDepth;
    }

    // 基于递归思路处理
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0; // 叶子节点的最大高度为0
        }
        // 递归计算左、右子树的最大高度
        int maxLeftDepth = dfs(node.left);
        int maxRightDepth = dfs(node.right);
        // 返回当前节点的最大高度
        return Math.max(maxLeftDepth, maxRightDepth) + 1;
    }
}
