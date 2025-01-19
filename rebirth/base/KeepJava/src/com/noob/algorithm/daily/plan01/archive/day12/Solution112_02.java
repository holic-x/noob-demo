package com.noob.algorithm.daily.plan01.archive.day12;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢112 路径之和
 */
public class Solution112_02 {

    // DFS
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return dfs(root, targetSum);
    }

    public boolean dfs(TreeNode node, int targetSum) {
        if (node == null) {
            return false;
        }

        // 判断当前叶子结点值是否和targetSum一致（此处用减法来找目标值）
        if (node.left == null && node.right == null) {
            if (node.val == targetSum) {
                return true;
            }
        }

        // 左、右节点存在满足条件的即可
        return dfs(node.left, targetSum - node.val) || dfs(node.right, targetSum - node.val);
    }
}
