package com.noob.algorithm.daily.plan03.hot100_random.day04.p008;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 110 平衡二叉树 - https://leetcode.cn/problems/balanced-binary-tree/description/
 */
public class Solution110_02 {

    /**
     * 思路分析：
     * 平衡二叉树的特性：左右子树的最大高度差不超过1
     */
    public boolean isBalanced(TreeNode root) {
        return dfs(root) != -1;
    }


    // 递归处理
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 计算左右子树的最大高度
        int maxLeft = dfs(node.left);
        int maxRight = dfs(node.right);

        // 判断高度是否在有效的取值范围内
        if (maxLeft == -1 || maxRight == -1) {
            return -1;
        }

        // 判断高度差
        if (Math.abs(maxLeft - maxRight) > 1) {
            return -1; // 返回一个无效的高度差进行校验（表示当前不符合平衡二叉树的要求）
        }

        // 返回当前节点的最大高度
        return Math.max(maxLeft, maxRight) + 1;
    }


}
