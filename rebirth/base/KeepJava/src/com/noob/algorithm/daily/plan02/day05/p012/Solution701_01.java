package com.noob.algorithm.daily.plan02.day05.p012;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟡 701 二叉搜索树中的插入操作 - https://leetcode.cn/problems/insert-into-a-binary-search-tree/description/
 */
public class Solution701_01 {

    /**
     * 思路分析：需确保插入后更新的树满足二叉搜索树特性，可能有多种结果
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 调用递归
        return dfs(root, val);
    }

    // 递归遍历构建
    private TreeNode dfs(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val);
        }

        // 校验节点值与val的关系
        int nodeVal = node.val;
        if (val < nodeVal) {
            // 需插在左边
            node.left = dfs(node.left, val);
        } else {
            // 需插在右边
            node.right = dfs(node.right, val);
        }

        // 返回节点
        return node;
    }
}
