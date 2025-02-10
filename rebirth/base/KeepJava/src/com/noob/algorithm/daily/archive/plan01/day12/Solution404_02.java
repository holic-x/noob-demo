package com.noob.algorithm.daily.archive.plan01.day12;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🟢404 左叶子之和
 */
public class Solution404_02 {

    public int leftSum = 0;

    // dfs思路
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root);

        // 返回结果
        return leftSum;
    }


    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        // 判断当前节点是否存在左叶子节点
        if (node.left != null && node.left.left == null && node.left.right == null) {
            leftSum += node.val;
        }

        // 递归处理左右节点
        dfs(node.left);
        dfs(node.right);
    }
}
