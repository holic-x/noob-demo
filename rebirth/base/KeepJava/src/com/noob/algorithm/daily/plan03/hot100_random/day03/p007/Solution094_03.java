package com.noob.algorithm.daily.plan03.hot100_random.day03.p007;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 🟢 094 二叉树的中序遍历 -  https://leetcode.cn/problems/binary-tree-inorder-traversal/description/
 */
public class Solution094_03 {

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
            // 一直向左遍历
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            // 访问节点
            cur = stack.pop();
            ans.add(cur.val);

            // 转向右子树
            cur = cur.right;
        }

        // 返回结果
        return ans;
    }

}
