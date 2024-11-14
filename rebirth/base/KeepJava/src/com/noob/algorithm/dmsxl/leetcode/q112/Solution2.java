package com.noob.algorithm.dmsxl.leetcode.q112;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 112 路径总和
 */
public class Solution2 {

    // 递归法
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return dfs(root, targetSum);
    }

    public boolean dfs(TreeNode node, int targetSum) {
        if (node == null) {
            return false;
        }

        // 遇到叶子节点（如果路径和匹配targetSum，则递归最终到叶子节点剩余值应该和当前遍历节点值一致才满足）
        if (node.left == null && node.right == null) {
            if (node.val == targetSum) {
                return true;
            }
        }

        // 左、右节点存在满足条件的即可
        return dfs(node.left, targetSum - node.val) || dfs(node.right, targetSum - node.val);
    }

}
