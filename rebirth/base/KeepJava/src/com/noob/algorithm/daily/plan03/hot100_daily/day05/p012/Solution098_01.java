package com.noob.algorithm.daily.plan03.hot100_daily.day05.p012;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 098.验证二叉搜索树 - https://leetcode.cn/problems/validate-binary-search-tree/description/
 */
public class Solution098_01 {

    /**
     * 思路分析：
     */
    public boolean isValidBST(TreeNode root) {
        return valid(root);
    }

    // ❌ 递归验证:只考虑到当前节点与左右子节点的关系，而没有处理到子树的范围验证
    private boolean valid(TreeNode node) {
        if (node == null) {
            return true;
        }

        // node != null 分析，校验当前节点与子节点的关系
        int curNodeVal = node.val;
        if (node.left != null && node.left.val >= curNodeVal) {
            return false;
        }
        if (node.right != null && node.right.val <= curNodeVal) {
            return false;
        }

        // 进一步递归检验左、右子节点
        boolean validLeft = valid(node.left);
        boolean validRight = valid(node.right);

        return validLeft && validRight;

    }
}
