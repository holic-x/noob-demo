package com.noob.algorithm.daily.plan03.hot100_random.day03.p007;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 🟢 144 二叉树的前序遍历 - https://leetcode.cn/problems/binary-tree-preorder-traversal/submissions/598678754/
 */
public class Solution144_02 {

    /**
     * 思路分析：
     * 迭代思路
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        // 构建结果集
        List<Integer> ans = new ArrayList<>();

        // 借助栈辅助处理
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ans.add(node.val);

            // DLR (栈：先进后出，此处右节点先进 左节点后进，遍历顺序才满足)
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        // 返回处理结果
        return ans;

    }

}
