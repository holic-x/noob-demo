package com.noob.algorithm.daily.plan01.archive.day27;

import com.noob.algorithm.daily.base.TreeNode;

/**
 * 🟢 543 二叉树的直径
 */
public class Solution543_01 {

    // 定义最长直径（全局参数）
    public int maxDiameter = 0;

    /**
     * 思路分析：对于遍历节点，每个节点都有可能作为这个遍历路径的root
     */
    public int diameterOfBinaryTree(TreeNode root) {
        // 调用dfs方法
        dfs(root);
        // 返回结果
        return maxDiameter;
    }

    // 递归求解
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // node 不为null，计算其左右子节点的深度
        int leftDepth = dfs(node.left);
        int rightDepth = dfs(node.right);
        // 更新直径最大值
        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);
        // 返回最大深度
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
