package com.noob.algorithm.daily.plan02.day04.p008;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.LinkedList;

/**
 * 🟢 222 完全二叉树的节点个数 - https://leetcode.cn/problems/count-complete-tree-nodes/description/
 */
public class Solution222_02 {

    private int cnt;

    /**
     * 思路分析：完全二叉树（除了最底层，其他层都填满）
     * 通用思路：遍历统计（迭代法-深度优先遍历）
     */
    public int countNodes(TreeNode root) {
        // 调用递归算法
        dfs(root);
        // 返回结果
        return cnt;
    }

    private void dfs(TreeNode node) {
        // 递归出口
        if (node == null) {
            return;
        }
        // DLR
        cnt++; // 统计节点
        dfs(node.left); // 递归处理左节点
        dfs(node.right); // 递归处理右节点
    }
}
