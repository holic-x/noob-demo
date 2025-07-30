package com.noob.algorithm.daily.plan03.hot100_daily.day03.p007;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 🟢 094 二叉树的中序遍历 -  https://leetcode.cn/problems/binary-tree-inorder-traversal/description/
 */
public class Solution094_02 {

    /**
     * 思路分析：LDR
     * - 迭代法：一路遍历左节点并加入集合
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<Integer> ans = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        TreeNode pt = root; // 遍历节点
        while (pt != null || !stack.isEmpty()) {
            // 一路向左
            while (pt != null) {
                stack.push(pt);
                pt = pt.left;
            }

            // 取出节点处理
            TreeNode node = stack.pop();
            ans.add(node.val);
            // 切换到右子树
            pt = node.right;
        }

        // 返回结果
        return ans;
    }


}
