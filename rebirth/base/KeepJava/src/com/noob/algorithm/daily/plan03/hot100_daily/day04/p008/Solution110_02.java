package com.noob.algorithm.daily.plan03.hot100_daily.day04.p008;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 110 平衡二叉树 - https://leetcode.cn/problems/balanced-binary-tree/description/
 */
public class Solution110_02 {

    /**
     * 思路分析：
     * 平衡二叉树：该树所有节点的左右子树的高度相差不超过 1
     * - 计算高度，随后校验高度差
     */
    public boolean isBalanced(TreeNode root) {

        int ans = dfs(root);

        // 如果返回正常高度则说明这棵数符合平衡二叉树特性
        return ans != -1;
        
    }


    // 计算高度、节点高度差校验
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // node 不为null，分别计算左右子树
        int leftHeight = dfs(node.left);
        int rightHeight = dfs(node.right);

        // 校验高度：如果出现异常的数据（用于标记高度差大于1的标记，则此时可以继续抛出这个结果）
        if (leftHeight == -1 || rightHeight == -1) {
            return -1;
        }

        // 计算高度差
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        // 返回高度（正常场景返回正常计算的高度数据）
        return Math.max(leftHeight, rightHeight) + 1;
    }


}
