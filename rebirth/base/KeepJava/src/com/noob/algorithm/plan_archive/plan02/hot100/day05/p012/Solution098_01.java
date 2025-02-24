package com.noob.algorithm.plan_archive.plan02.hot100.day05.p012;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 098.验证二叉搜索树 - https://leetcode.cn/problems/validate-binary-search-tree/description/
 */
public class Solution098_01 {

    /**
     * 校验节点是否为一个有效的二叉搜索树
     */
    public boolean isValidBST(TreeNode root) {
        return valid(root);
    }

    // 递归思路(❌ 此处设定会忽略[5,4,6,null,null,3,7]这种情况，无法满足节点的左侧均小、右侧均大的设定)
    private boolean valid(TreeNode node) {
        if (node == null) {
            return true;
        }

        // 校验节点
        if (node.left != null) {
            if (node.left.val >= node.val) {
                return false;
            }
        }
        if (node.right != null) {
            if (node.right.val <= node.val) {
                return false;
            }
        }

        // 递归校验左、右子树是否满足二叉搜索特性
        boolean validLeft = valid(node.left);
        boolean validRight = valid(node.right);
        return validLeft && validRight;
    }
}
