package com.noob.algorithm.daily.archive.plan02.hot100.day04.p008;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 222 完全二叉树的节点个数 - https://leetcode.cn/problems/count-complete-tree-nodes/description/
 */
public class Solution222_03 {

    /**
     * 思路分析：完全二叉树（除了最底层，其他层都填满）
     * 通用思路：遍历统计（迭代法-深度优先遍历）
     */
    public int countNodes(TreeNode root) {
        // 调用递归算法
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        // 返回结果
        return list.size();
    }

    private void dfs(TreeNode node, List<Integer> list) {
        // 递归出口
        if (node == null) {
            return;
        }
        // DLR
        list.add(node.val); // 统计节点
        dfs(node.left, list); // 递归处理左节点
        dfs(node.right, list); // 递归处理右节点
    }
}
