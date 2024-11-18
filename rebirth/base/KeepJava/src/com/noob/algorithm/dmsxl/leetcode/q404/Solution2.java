package com.noob.algorithm.dmsxl.leetcode.q404;

import com.noob.algorithm.dmsxl.baseStructure.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 404 左叶子之和
 */
public class Solution2 {

    int leftSum = 0;

    // DFS 思路
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return leftSum;
    }

    // DFS(DLR)
    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        // 判断当前节点是否存在"左叶子"
        if (node.left != null && node.left.left == null && node.left.right == null) {
            // 存在左叶子：累加和
            leftSum += node.left.val;
        }

        // 递归遍历左右子节点
        dfs(node.left);
        dfs(node.right);
    }
}
