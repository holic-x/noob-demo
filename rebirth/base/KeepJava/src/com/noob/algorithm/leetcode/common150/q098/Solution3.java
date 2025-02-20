package com.noob.algorithm.leetcode.common150.q098;

import com.noob.algorithm.leetcode.common150.base.TreeNode;

/**
 * 098 验证二叉搜索树
 */
public class Solution3 {

    long pre = Long.MIN_VALUE;

    /**
     * 递归方式：中序遍历
     */
    public boolean isValidBST(TreeNode root) {
        return validBSTByInOrder(root);
    }


    public boolean validBSTByInOrder(TreeNode node) {
        if (node == null) {
            return true;
        }
        if(!validBSTByInOrder(node.left) || node.val <= pre){
            return false;
        }
        pre = node.val;
        return validBSTByInOrder(node.right);
    }
}
