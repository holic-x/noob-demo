package com.noob.algorithm.plan_archive.plan02.hot100.day03.p007;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 🟢 145 后序遍历 - https://leetcode.cn/problems/binary-tree-postorder-traversal/
 */
public class Solution145_02 {

    /**
     * 迭代思路：LRD
     * - ① 逆序：DRL
     * - ② 回归前序遍历思路，构建辅助栈，此处则是L左节点先入栈、R右节点后入栈
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        // 定义结果集
        List<Integer> res = new ArrayList<>();
        // 构建栈辅助遍历
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        // DRL的遍历顺序（先入左后入右）
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            // 节点入栈（先进后出，先左后右）
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        // 返回结果集（反转成LRD）
        Collections.reverse(res);
        return res;
    }
}