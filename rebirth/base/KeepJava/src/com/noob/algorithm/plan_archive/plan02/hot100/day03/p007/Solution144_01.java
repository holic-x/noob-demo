package com.noob.algorithm.plan_archive.plan02.hot100.day03.p007;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 144 二叉树的前序遍历 - https://leetcode.cn/problems/binary-tree-preorder-traversal/submissions/598678754/
 */
public class Solution144_01 {

    /**
     * 递归思路
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        // 前序遍历
        preorder(root, list);
        // 返回结果
        return list;
    }

    // dfs：前序遍历（DLR）
    private void preorder(TreeNode node, List<Integer> list) {
        // 递归出口
        if (node == null) {
            return;
        }

        // DLR
        list.add(node.val);
        preorder(node.left, list);
        preorder(node.right, list);
    }

}
