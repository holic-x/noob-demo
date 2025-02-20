package com.noob.algorithm.daily.archive.plan02.hot100.day03.p007;

import com.noob.algorithm.leetcode.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 🟢 144 二叉树的前序遍历 - https://leetcode.cn/problems/binary-tree-preorder-traversal/submissions/598678754/
 */
public class Solution144_02 {

    /**
     * 迭代思路：DLR（基于辅助栈构建遍历顺序）
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        // 定义结果集
        List<Integer> res = new ArrayList<>();
        // 构建栈辅助遍历
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        // DLR(前序遍历)
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            // 节点入栈（先进后出，先将右节点入栈）
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        // 返回结果集
        return res;
    }

}
