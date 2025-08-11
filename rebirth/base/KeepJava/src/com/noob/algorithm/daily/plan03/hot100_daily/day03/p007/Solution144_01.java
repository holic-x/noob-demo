package com.noob.algorithm.daily.plan03.hot100_daily.day03.p007;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 🟢 144 二叉树的前序遍历 - https://leetcode.cn/problems/binary-tree-preorder-traversal/submissions/598678754/
 */
public class Solution144_01 {


    /**
     * 思路分析：DLR
     * 构建栈辅助遍历
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        // 迭代思路
        if (root == null) {
            return Collections.emptyList();
        }

        List<Integer> ans = new ArrayList<>();
        // 构建栈辅助遍历
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ans.add(node.val);
            // 先右后左
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return ans;
    }


}
