package com.noob.algorithm.daily.archive.plan02.hot100.day03.p007;

import com.noob.algorithm.leetcode.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 094 二叉树的中序遍历 -  https://leetcode.cn/problems/binary-tree-inorder-traversal/description/
 */
public class Solution094_01 {

    /**
     * 递归思路
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        // 前序遍历
        inorder(root, list);
        // 返回结果
        return list;
    }

    // dfs：中序遍历（LDR）
    private void inorder(TreeNode node, List<Integer> list) {
        // 递归出口
        if (node == null) {
            return;
        }

        // LDR
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }

}
