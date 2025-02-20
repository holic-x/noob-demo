package com.noob.algorithm.daily.archive.plan02.hot100.day03.p007;

import com.noob.algorithm.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 🟢 094 二叉树的中序遍历 -  https://leetcode.cn/problems/binary-tree-inorder-traversal/description/
 */
public class Solution094_02 {

    /**
     * 迭代思路：LDR
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> list = new ArrayList<>();
        // 构建栈辅助遍历
        Stack<TreeNode> stack = new Stack<>();
        // stack.push(root);
        // 定义遍历指针
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            // 如果存在左节点则入栈
            if (cur != null) {
                // 将遍历节点依次入栈
                stack.push(cur);
                cur = cur.left; // 向左遍历
            } else if (cur == null) {
                // 到达了最左节点，则可取出栈顶元素处理，随后将cur指针指向右节点
                TreeNode node = stack.pop();
                list.add(node.val);
                cur = node.right; // 将遍历指针指向当前取出节点的右节点
            }
        }

        // 返回结果集
        return list;
    }

}
