package com.noob.algorithm.daily.plan03.hot100_daily.day03.p006;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 🟢 111 二叉树的最小深度 - https://leetcode.cn/problems/minimum-depth-of-binary-tree/description/
 */
public class Solution111_02 {

    /**
     * 思路分析：最小深度（根节点到最近的叶子节点的最短路径上的节点数量）
     * 回溯法：路径处理
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        path.add(root.val);
        dfs(root);
        return min;
    }


    public int min = Integer.MAX_VALUE;
    public List<Integer> path = new ArrayList<>();


    // 回溯
    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        // 叶子节点
        if (node.left == null && node.right == null) {
            min = Math.min(min, path.size());
        }

        // 左右节点处理
        if (node.left != null) {
            path.add(node.left.val);
            dfs(node.left);
            path.remove(path.size() - 1);
        }

        if (node.right != null) {
            path.add(node.right.val);
            dfs(node.right);
            path.remove(path.size() - 1);
        }

    }

}
