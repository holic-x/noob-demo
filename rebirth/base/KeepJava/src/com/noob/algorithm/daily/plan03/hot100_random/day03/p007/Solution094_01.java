package com.noob.algorithm.daily.plan03.hot100_random.day03.p007;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 094 二叉树的中序遍历 -  https://leetcode.cn/problems/binary-tree-inorder-traversal/description/
 */
public class Solution094_01 {

    /**
     * 思路分析：
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list;
    }


    // 递归法
    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        // 中序遍历（LDR）
        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }


}
