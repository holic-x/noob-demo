package com.noob.algorithm.daily.plan03.hot100_daily.day05.p012;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 530 二叉搜索树的最小绝对差 - https://leetcode.cn/problems/minimum-absolute-difference-in-bst/submissions/599208140/
 */
public class Solution530_01 {

    /**
     * 利用二叉搜索树特性：LDR 序列有序（小->大），记录并校验每个差值，得到min
     *
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return minSub;
    }

    private TreeNode preNode = null; // 记录当前遍历节点的上一个节点
    private int minSub = Integer.MAX_VALUE; // 定义最小差值

    // dfs：LDR 中序遍历
    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);

        // 节点处理
        if (preNode != null) {
            minSub = Math.min(minSub, Math.abs(node.val - preNode.val));
        }
        preNode = node;

        dfs(node.right);
    }

}
