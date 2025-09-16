package com.noob.algorithm.daily.plan03.hot100_daily.day05.p012;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 669 修剪二叉搜索树 - https://leetcode.cn/problems/trim-a-binary-search-tree/description/ todo
 */
public class Solution669_02 {

    /**
     * 思路分析：迭代思路，寻找第一个满足在[low,right]区间内的节点，基于该节点的左右子树分别进行两边校验
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        return dfs(root, low, high);
    }

    // 回归递归思路
    private TreeNode dfs(TreeNode node, int low, int high) {
        if (node == null) {
            return null;
        }

        // 处理节点，判断当前节点值是否在[low,right]有效范围内
        int nodeVal = node.val;
        if (nodeVal < low) {
            return dfs(node.right, low, high);
        } else if (nodeVal > high) {
            return dfs(node.left, low, high);
        } else {
            // node 节点在[low,high]有效范围内，处理其左右子树
            node.left = dfs(node.left, low, high);
            node.right = dfs(node.right, low, high);
        }
        return node;
    }

}
