package com.noob.algorithm.daily.plan03.hot100_daily.day03.p007;

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
     * 思路分析：LRD
     * 先DRL随后反转（逆序）
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        // 迭代思路
        if (root == null) {
            return Collections.emptyList();
        }

        List<Integer> ans = new ArrayList<>();
        // 构建栈辅助遍历
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ans.add(node.val);
            // 先左后右
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        // 返回反转顺序
        Collections.reverse(ans);
        return ans;
    }



}
