package com.noob.algorithm.daily.codeTop;

import com.noob.algorithm.plan_archive.baseStructure.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    /**
     * 平衡二叉树的左右子树的最大高度差不超过1
     */
    public boolean isBalanced(TreeNode root) {
        int res = dfs(root);
        // 校验获取的结果是否为-1（如果为-1说明出现了不平衡）
        return res != -1;
    }

    /**
     * 递归校验平衡二叉树: 在一个递归方法中获取子树高度的同时校验高度差，用一个不可能存在的高度来标识【高度差大于1】的情况
     * - 如果递归过程中校验发现【高度差大于1】，则返回-1（用于标记不平衡的情况，然后一层一层将异常的高度差往上抛）
     * - 如果递归过程中校验满足平衡条件，则正常返回高度
     */
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 递归计算左子树的最大高度
        int leftMaxDepth = dfs(node.left);
        int rightMaxDepth = dfs(node.right);

        // 校验获取的高度数据是否正常，如果出现异常数据说明非平衡（继续将异常数据上抛）
        if (leftMaxDepth == -1 || rightMaxDepth == -1) {
            return -1;
        } else {
            // 校验左右子树的高度差是否大于1
            if (Math.abs(leftMaxDepth - rightMaxDepth) > 1) {
                return -1; // 如果大于1，则说明不满足平衡条件，则返回-1（异常数据标识不平衡的情况）
            } else {
                return Math.max(leftMaxDepth, rightMaxDepth) + 1; // 返回正常的高度数据
            }
        }
    }

}
