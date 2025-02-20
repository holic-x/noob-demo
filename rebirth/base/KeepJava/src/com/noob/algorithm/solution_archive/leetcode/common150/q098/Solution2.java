package com.noob.algorithm.solution_archive.leetcode.common150.q098;

import com.noob.algorithm.solution_archive.leetcode.common150.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 098 验证二叉搜索树
 */
public class Solution2 {

    /**
     * 递归方式：前序遍历
     * 此处用long接收参数（避免数值溢出问题）
     * 因为二叉搜索树序满足：left < root < right 如果left取到int的最大值，那么root、right必须必这个MIN_INT要大，不符合场景，因此需要用long
     */
    public boolean isValidBST(TreeNode root) {
        return validBSTByPreOrder(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean validBSTByPreOrder(TreeNode node, long leftVal, long rightVal) {
        if (node == null) {
            return true;
        }
        long x = node.val;
        return leftVal < x && x < rightVal && validBSTByPreOrder(node.left, leftVal, x) && validBSTByPreOrder(node.right, x, rightVal);
    }

}
