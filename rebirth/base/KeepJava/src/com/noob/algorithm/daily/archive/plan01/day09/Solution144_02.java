package com.noob.algorithm.daily.archive.plan01.day09;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 🟢 144 二叉树的前序遍历
 */
public class Solution144_02 {

    // 迭代思路：DLR
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        // 定义结果集
        List<Integer> res = new ArrayList<>();
        // 构建栈辅助遍历
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        // 处理栈元素
        while (!stack.isEmpty()) {
            // 先遍历根节点
            TreeNode cur = stack.pop();
            res.add(cur.val);
            // 先将右节点入栈（出栈时先入后出满足DLR）
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }

        // 返回结果集
        return res;
    }
}
