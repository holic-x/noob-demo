package com.noob.algorithm.common150.q226;

import com.noob.algorithm.common150.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 226 翻转二叉树
 */
public class Solution2 {

    /**
     * 思路：递归
     */
    public TreeNode invertTree(TreeNode root) {
        if(root==null){
            return root; // 节点为空，不需要执行操作
        }
        // 节点不为空，递归交换其左右子树
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // 递归交换
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
