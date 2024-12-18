package com.noob.algorithm.plan01.day09;

import com.noob.algorithm.dmsxl.baseStructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 🟢 145 二叉树的后序遍历
 */
public class Solution145_02 {
    // 迭代法（LRD）
    List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        // 初始化结果集
        List<Integer> res = new ArrayList<>();

        // 构建栈辅助遍历
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root); // 初始化

        // 遍历栈
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.add(cur.val); // D
            // 构建"DRL"的遍历顺序，因此此处入栈顺序是先L后R
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }

        // 上述遍历构建生成DRL的序列，对该序列进行逆序得到LRD后序遍历序列
        Collections.reverse(res);
        // 返回结果
        return res;
    }

}
