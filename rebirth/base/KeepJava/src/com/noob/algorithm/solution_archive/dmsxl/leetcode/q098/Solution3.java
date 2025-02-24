package com.noob.algorithm.solution_archive.dmsxl.leetcode.q098;

import com.noob.algorithm.solution_archive.dmsxl.baseStructure.tree.TreeNode;

/**
 * 098 验证二叉搜索树
 */
public class Solution3 {

    // DFS 前序遍历思路
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // 前序遍历思路
    public boolean dfs(TreeNode node, int minVal, int maxVal) {
        if (node == null) {
            return true;
        }
        // 当前节点值
        int cur = node.val;
        // 需满足二叉搜索树属性left<cur<right，且其子树也要满足这个特性
        return cur > minVal && cur < maxVal && dfs(node.left, minVal, cur) && dfs(node.right, cur, maxVal);
    }

}
