package com.noob.algorithm.daily.plan01.archive.day09;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 🟢 094 二叉树的中序遍历
 */
public class Solution094_02 {

    // 中序遍历（迭代法）LDR
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        // 定义结果集
        List<Integer> res = new ArrayList<>();

        // 构建辅助栈
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root; // 定义cur指针用于遍历
        // 当指针不为空或者栈不为空时进行处理
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur); // 将访问的节点入栈
                cur = cur.left; // 一直向左遍历直到到达最左的子节点
            } else {
                /*
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
                 */
                TreeNode node = stack.pop();
                res.add(node.val);
                cur = node.right;
            }
        }
        // 返回结果
        return res;
    }

}
