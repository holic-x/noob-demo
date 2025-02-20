package com.noob.algorithm.plan_archive.plan02.hot100.day05.p012;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟢 700 二叉搜索树中的搜索 - https://leetcode.cn/problems/search-in-a-binary-search-tree/description/
 */
public class Solution700_02 {

    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode findNode = search(root, val);
        return findNode;
    }

    /**
     * 递归搜索：基于DLR顺序递归遍历，利用二叉搜索树特性：
     * 节点左侧的均比它小，节点右侧的均比他大，基于此来选定搜索区域
     */
    private TreeNode search(TreeNode node, int val) {
        if (node == null) {
            return null;
        }

        TreeNode findNode = null;
        if (node.val == val) {
            return node;
        } else if (node.val > val) {
            // 目标值在左子树
            findNode = search(node.left, val);
        } else if (node.val < val) {
            // 目标值在右子树
            findNode = search(node.right, val);
        }
        // 返回查找结果
        return findNode;
    }
}
