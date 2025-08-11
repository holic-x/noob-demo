package com.noob.algorithm.daily.plan03.hot100_daily.day03.p007;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 094 二叉树的中序遍历 -  https://leetcode.cn/problems/binary-tree-inorder-traversal/description/
 */
public class Solution094_01 {

    /**
     * 思路分析：LDR
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        dfs(root);
        return ans;
    }

    List<Integer> ans = new ArrayList<>();

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        dfs(node.left);    // 左子树处理
        ans.add(node.val); // 记录结果
        dfs(node.right);   // 右子树处理
    }

}
