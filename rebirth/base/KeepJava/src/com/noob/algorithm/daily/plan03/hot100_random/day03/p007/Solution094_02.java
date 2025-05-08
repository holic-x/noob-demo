package com.noob.algorithm.daily.plan03.hot100_random.day03.p007;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 🟢 094 二叉树的中序遍历 -  https://leetcode.cn/problems/binary-tree-inorder-traversal/description/
 */
public class Solution094_02 {

    /**
     * 思路分析：
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        // 迭代法
        Stack<TreeNode> stack = new Stack<>();

        // 中序遍历：LDR
        TreeNode cur = root;

        List<Integer> ans = new ArrayList<>();

        // 指针不为空或者栈不为空时进行处理
        while (cur != null || !stack.isEmpty()) {
            // 将访问到的节点入栈，一直向左遍历
            if (cur != null) {
                stack.push(cur); // 将访问到的节点入栈
                cur = cur.left; // 一直向左遍历
            } else {
                TreeNode node = stack.pop();
                ans.add(node.val);
                cur = node.right;
            }
        }

        // 返回结果
        return ans;
    }

}
