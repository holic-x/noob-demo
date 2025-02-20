package com.noob.algorithm.leetcode.common150.q112;

import com.noob.algorithm.leetcode.common150.base.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 112 路径之和
 */
public class Solution2 {
    /**
     * 递归
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // 递归出口
        if (root == null) {
            return false;
        }
        // 叶子节点判断
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        // 递归判断
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
}
