package com.noob.algorithm.daily.plan03.hot100_daily.day05.p012;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * 🟡 098.验证二叉搜索树 - https://leetcode.cn/problems/validate-binary-search-tree/description/
 */
public class Solution098_02 {

    /**
     * 思路分析：递归校验节点值是否在有效的[min,max]范围
     */
    public boolean isValidBST(TreeNode root) {
        return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // 递归验证:版本优化（基于范围验证概念:覆盖子树验证范围）
    private boolean valid(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }

        // node != null 分析，验证节点值是否在有效的范围内，如果不在则说明不满足BST验证规则
        if (node.val <= min || node.val >= max) {
            return false;
        }

        // 进一步递归检验左、右子节点
        boolean validLeft = valid(node.left, min, node.val); // 对于满足BST的左子树来说，最大节点即为当前节点
        boolean validRight = valid(node.right, node.val, max);// 对于满足BST的右子树来说，最小节点即为当前节点

        return validLeft && validRight;

    }
}
