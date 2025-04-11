package com.noob.algorithm.daily.codeTop;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 958 二叉树的完全性校验：https://leetcode.cn/problems/check-completeness-of-a-binary-tree/
 */
public class Solution958_01 {

    private int size = 0; // 树节点个数
    private int maxCode = 0; // 节点编号

    /**
     * 思路分析：给定一个根节点root，判断这棵树是否为一个完全二叉树
     * 完全二叉树：除了最后一层之外所有层均被完全填满，且最后一层的所有节点尽可能靠左
     */
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 递归遍历
        dfs(root, 1);
        // 返回结果
        return size == maxCode;
    }

    // 递归遍历，统计树节点个数并标记节点编号
    private void dfs(TreeNode node, int index) {
        if (node == null) {
            return;
        }

        // 统计树节点个数,更新最大节点编号
        size++;
        maxCode = Math.max(maxCode, index);

        // 递归处理
        dfs(node.left, index * 2);
        dfs(node.right, index * 2 + 1);
    }

}