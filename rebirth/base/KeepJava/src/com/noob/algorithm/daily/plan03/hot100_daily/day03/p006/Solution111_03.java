package com.noob.algorithm.daily.plan03.hot100_daily.day03.p006;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 111 二叉树的最小深度 - https://leetcode.cn/problems/minimum-depth-of-binary-tree/description/
 */
public class Solution111_03 {

    /**
     * 思路分析：最小深度（根节点到最近的叶子节点的最短路径上的节点数量）
     * 回溯法：递归思路
     */
    public int minDepth(TreeNode root) {
        return dfs(root);
    }

    // 递归处理：根据node节点判断
    private int dfs(TreeNode node) {
        // 根据node是否为null分情况讨论
        if (node == null) {
            return 0;
        }

        // node 不为null，校验左、右子树
        TreeNode L = node.left;
        TreeNode R = node.right;

        // ① L、R均为null
        if (L == null && R == null) {
            return 1; // 叶子节点：最小深度即为1
        }

        // ② L、R 中有一个不为null
        if ((L == null && R != null) || (L != null && R == null)) {
            return dfs(L) + dfs(R) + 1; // L+R+1
        }

        // ③ L、R 均不为null，选择较小深度
        return Math.min(dfs(L), dfs(R)) + 1;
    }

}
