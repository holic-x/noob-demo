package com.noob.algorithm.daily.plan03.hot100_daily.day04.p008;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 222 完全二叉树的节点个数 - https://leetcode.cn/problems/count-complete-tree-nodes/description/
 */
public class Solution222_01 {

    /**
     * 思路分析：
     * 统计节点个数（回归到遍历的思路）
     */
    public int countNodes(TreeNode root) {
        dfs(root);
        return cnt;
    }

    private int cnt;

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        cnt++;
        dfs(node.left);
        dfs(node.right);

    }
}
