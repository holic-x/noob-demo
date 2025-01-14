package com.noob.algorithm.daily.plan01.day28;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 🟢 094 二叉树的中序遍历 - https://leetcode.cn/problems/binary-tree-inorder-traversal/description/
 */
public class Solution094_02 {

    public List<Integer> inorderTraversal(TreeNode root) {
        // 调用方法进行遍历
        return ldr(root);
    }

    // 迭代法
    private List<Integer> ldr(TreeNode node) {
        List<Integer> list = new ArrayList<>();

        // 构建栈辅助遍历
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = node; // 构建遍历指针
        while (cur != null || !stack.isEmpty()) { // 指针指向节点不为空或者栈不为空则继续处理
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                // 取出节点
                TreeNode curNode = stack.pop();
                list.add(curNode.val);
                cur = curNode.right;
            }
        }
        return list;
    }

}
