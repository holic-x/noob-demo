package com.noob.algorithm.daily.archive.plan02.hot100.day04.p010;

import com.noob.algorithm.daily.base.TreeNode;

import java.util.LinkedList;

/**
 * 🟢 404 左叶子之和 - https://leetcode.cn/problems/sum-of-left-leaves/description/
 */
public class Solution404_02 {

    private int sum = 0;

    /**
     * 思路分析：递归思路
     */
    public int sumOfLeftLeaves(TreeNode root) {
        dfs(root);
        return sum;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        // 校验节点是否存在左叶子节点
        if (node.left != null && node.left.left == null && node.left.right == null) {
            sum += node.left.val;
        }

        // 递归遍历左、右子树
        dfs(node.left);
        dfs(node.right);
    }
}
