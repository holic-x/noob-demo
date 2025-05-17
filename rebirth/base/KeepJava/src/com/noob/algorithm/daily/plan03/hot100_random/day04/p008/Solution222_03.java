package com.noob.algorithm.daily.plan03.hot100_random.day04.p008;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 222 完全二叉树的节点个数 - https://leetcode.cn/problems/count-complete-tree-nodes/description/
 */
public class Solution222_03 {

    /**
     * 思路分析：
     * 统计节点个数可以通过遍历的方式进行统计，例如DFS、BFS统计
     */
    public int countNodes(TreeNode root) {
        int ans = dfs(root);
        return ans;
    }

    // 递归处理: L+R+1 的递归思路
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 判断当前节点是否为满二叉树（计算左右子节点的深度）
        TreeNode curLeft = node.left;
        int leftDepth = 0;
        while (curLeft != null) {
            leftDepth++;
            curLeft = curLeft.left;
        }

        TreeNode curRight = node.right;
        int rightDepth = 0;
        while (curRight != null) {
            rightDepth++;
            curRight = curRight.right;
        }

        if (leftDepth == rightDepth) {
            return (2 << leftDepth) - 1; // 直接统计节点个数
        }

        // 否则按照正常的情况处理
        int L = dfs(node.left);
        int R = dfs(node.right);
        return L + R + 1;
    }
}
