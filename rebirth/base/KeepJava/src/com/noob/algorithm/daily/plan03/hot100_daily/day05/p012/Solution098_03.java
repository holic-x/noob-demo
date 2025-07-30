package com.noob.algorithm.daily.plan03.hot100_daily.day05.p012;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 098.验证二叉搜索树 - https://leetcode.cn/problems/validate-binary-search-tree/description/
 */
public class Solution098_03 {

    /**
     * 思路分析：BST的LDR是一个有序序列
     */
    public boolean isValidBST(TreeNode root) {
        return valid(root);
    }

    // 初始化
    private long preVal = Long.MIN_VALUE;

    private boolean valid(TreeNode node) {
        if (node == null) {
            return true;
        }

        // node 不为 null：LDR
        boolean validLeft = valid(node.left);

        if (preVal >= node.val) {
            return false;
        }

        preVal = node.val;

        boolean validRight = valid(node.right);

        return validLeft && validRight;

    }
}
