package com.noob.algorithm.daily.plan03.hot100_random.day03.p006;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 104 二叉树的最大深度 - https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/
 */
public class Solution104_01 {

    /**
     * 思路分析：
     */
    public int maxDepth(TreeNode root) {
        int max = dfs(root);
        return max;
    }


    // 递归处理思路
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 计算左子树的最大深度
        int leftMaxDepth = dfs(node.left);
        // 计算右子树的最大深度
        int rightMaxDepth = dfs(node.right);

        // 返回当前节点的最大深度
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }

}
